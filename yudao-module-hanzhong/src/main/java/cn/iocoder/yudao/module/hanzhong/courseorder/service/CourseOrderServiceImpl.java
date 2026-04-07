package cn.iocoder.yudao.module.hanzhong.courseorder.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.course.dal.dataobject.CourseDO;
import cn.iocoder.yudao.module.hanzhong.course.dal.mysql.CourseMapper;
import cn.iocoder.yudao.module.hanzhong.course.service.CourseService;
import cn.iocoder.yudao.module.hanzhong.courseorder.controller.admin.vo.CourseOrderPageReqVO;
import cn.iocoder.yudao.module.hanzhong.courseorder.controller.app.vo.AppCourseOrderCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.courseorder.controller.app.vo.AppCourseOrderPageReqVO;
import cn.iocoder.yudao.module.hanzhong.courseorder.dal.dataobject.CourseOrderDO;
import cn.iocoder.yudao.module.hanzhong.courseorder.dal.mysql.CourseOrderMapper;
import cn.iocoder.yudao.module.hanzhong.message.service.MessageService;
import cn.iocoder.yudao.module.hanzhong.studyrecord.dal.dataobject.StudyRecordDO;
import cn.iocoder.yudao.module.hanzhong.studyrecord.dal.mysql.StudyRecordMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.hanzhong.enums.ErrorCodeConstants.COURSE_ALREADY_ORDERED;
import static cn.iocoder.yudao.module.hanzhong.enums.ErrorCodeConstants.COURSE_NOT_EXISTS;
import static cn.iocoder.yudao.module.hanzhong.enums.ErrorCodeConstants.COURSE_ORDER_NOT_EXISTS;
import static cn.iocoder.yudao.module.hanzhong.enums.ErrorCodeConstants.COURSE_ORDER_CANNOT_REFUND;
import static cn.iocoder.yudao.module.hanzhong.enums.ErrorCodeConstants.COURSE_ORDER_ALREADY_REFUND_REQUESTED;
import static cn.iocoder.yudao.module.hanzhong.enums.ErrorCodeConstants.COURSE_ORDER_NOT_IN_REFUND_REQUESTED;
import static cn.iocoder.yudao.module.hanzhong.enums.ErrorCodeConstants.COURSE_ORDER_CANNOT_DELETE;

/**
 * 汉中 课程订单 Service 实现类
 *
 * @author hanzhong
 */
@Service
@Validated
public class CourseOrderServiceImpl implements CourseOrderService {

    /** 订单状态：待支付 */
    private static final int ORDER_STATUS_PENDING = 0;
    /** 订单状态：已支付 */
    private static final int ORDER_STATUS_PAID = 1;
    /** 订单状态：已取消 */
    private static final int ORDER_STATUS_CANCELLED = 2;
    /** 订单状态：已退款（管理员处理完成） */
    private static final int ORDER_STATUS_REFUNDED = 3;
    /** 订单状态：退款申请中（用户申请，等待管理员审核） */
    private static final int ORDER_STATUS_REFUND_REQUESTED = 4;
    /** 订单状态：退款拒绝（管理员拒绝退款申请） */
    private static final int ORDER_STATUS_REFUND_REJECTED = 5;

    @Resource
    private CourseOrderMapper courseOrderMapper;

    @Resource
    private CourseMapper courseMapper;

    @Resource
    @Lazy
    private CourseService courseService;

    @Resource
    private StudyRecordMapper studyRecordMapper;

    @Resource
    @Lazy
    private MessageService messageService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createOrder(Long userId, AppCourseOrderCreateReqVO createReqVO) {
        CourseDO course = courseMapper.selectById(createReqVO.getCourseId());
        if (course == null) {
            throw exception(COURSE_NOT_EXISTS);
        }
        // 校验是否已有有效订单
        if (courseOrderMapper.selectActiveByUserIdAndCourseId(userId, createReqVO.getCourseId()) != null) {
            throw exception(COURSE_ALREADY_ORDERED);
        }
        CourseOrderDO order = new CourseOrderDO();
        order.setUserId(userId);
        order.setCourseId(course.getId());
        order.setCourseName(course.getTitle());
        order.setCoverUrl(course.getCoverUrl());
        order.setPrice(course.getPrice());
        order.setPaymentMethod(createReqVO.getPaymentMethod() != null ? createReqVO.getPaymentMethod() : "wechat");
        order.setStatus(ORDER_STATUS_PENDING);
        order.setOrderNo(generateOrderNo());
        courseOrderMapper.insert(order);
        // 增加课程报名人数
        courseService.incrementEnrollCount(course.getId());
        // 免费课程（price=0）直接自动确认支付并初始化学习记录
        if (course.getPrice() == null || course.getPrice() == 0) {
            CourseOrderDO updateObj = new CourseOrderDO();
            updateObj.setId(order.getId());
            updateObj.setStatus(ORDER_STATUS_PAID);
            updateObj.setPayTime(LocalDateTime.now());
            courseOrderMapper.updateById(updateObj);
            order.setStatus(ORDER_STATUS_PAID);
            initStudyRecord(order);
            sendOrderStatusMessage(order, ORDER_STATUS_PAID);
        }
        return order.getId();
    }

    /**
     * 生成订单号：年月日时分秒毫秒 + 4位随机数，确保在毫秒级别的唯一性
     */
    private static String generateOrderNo() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        String random = String.format("%04d", ThreadLocalRandom.current().nextInt(10000));
        return timestamp + random;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void payOrder(Long id, Long userId) {
        CourseOrderDO order = courseOrderMapper.selectById(id);
        if (order == null || !order.getUserId().equals(userId)) {
            throw exception(COURSE_ORDER_NOT_EXISTS);
        }
        if (order.getStatus() != ORDER_STATUS_PENDING) {
            // 非待支付状态，直接忽略（幂等）
            return;
        }
        CourseOrderDO updateObj = new CourseOrderDO();
        updateObj.setId(id);
        updateObj.setStatus(ORDER_STATUS_PAID);
        updateObj.setPayTime(LocalDateTime.now());
        courseOrderMapper.updateById(updateObj);
        // 自动初始化学习记录
        initStudyRecord(order);
        // 发送支付成功通知
        sendOrderStatusMessage(order, ORDER_STATUS_PAID);
    }

    @Override
    public void cancelOrder(Long id, Long userId) {
        CourseOrderDO order = courseOrderMapper.selectById(id);
        if (order == null) {
            throw exception(COURSE_ORDER_NOT_EXISTS);
        }
        if (!order.getUserId().equals(userId)) {
            throw exception(COURSE_ORDER_NOT_EXISTS);
        }
        CourseOrderDO updateObj = new CourseOrderDO();
        updateObj.setId(id);
        updateObj.setStatus(ORDER_STATUS_CANCELLED);
        updateObj.setCancelTime(LocalDateTime.now());
        courseOrderMapper.updateById(updateObj);
        // 发送取消通知
        sendOrderStatusMessage(order, ORDER_STATUS_CANCELLED);
    }

    @Override
    public void requestRefund(Long id, Long userId) {
        CourseOrderDO order = courseOrderMapper.selectById(id);
        if (order == null || !order.getUserId().equals(userId)) {
            throw exception(COURSE_ORDER_NOT_EXISTS);
        }
        if (order.getStatus() == ORDER_STATUS_REFUND_REQUESTED) {
            throw exception(COURSE_ORDER_ALREADY_REFUND_REQUESTED);
        }
        if (order.getStatus() != ORDER_STATUS_PAID) {
            throw exception(COURSE_ORDER_CANNOT_REFUND);
        }
        CourseOrderDO updateObj = new CourseOrderDO();
        updateObj.setId(id);
        updateObj.setStatus(ORDER_STATUS_REFUND_REQUESTED);
        courseOrderMapper.updateById(updateObj);
        // 发送退款申请通知（告知用户已提交）
        sendOrderStatusMessage(order, ORDER_STATUS_REFUND_REQUESTED);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void approveRefund(Long id) {
        CourseOrderDO order = courseOrderMapper.selectById(id);
        if (order == null) {
            throw exception(COURSE_ORDER_NOT_EXISTS);
        }
        if (order.getStatus() != ORDER_STATUS_REFUND_REQUESTED) {
            throw exception(COURSE_ORDER_NOT_IN_REFUND_REQUESTED);
        }
        CourseOrderDO updateObj = new CourseOrderDO();
        updateObj.setId(id);
        updateObj.setStatus(ORDER_STATUS_REFUNDED);
        courseOrderMapper.updateById(updateObj);
        // 通知用户退款已审批通过
        sendOrderStatusMessage(order, ORDER_STATUS_REFUNDED);
    }

    @Override
    public void rejectRefund(Long id) {
        CourseOrderDO order = courseOrderMapper.selectById(id);
        if (order == null) {
            throw exception(COURSE_ORDER_NOT_EXISTS);
        }
        if (order.getStatus() != ORDER_STATUS_REFUND_REQUESTED) {
            throw exception(COURSE_ORDER_NOT_IN_REFUND_REQUESTED);
        }
        CourseOrderDO updateObj = new CourseOrderDO();
        updateObj.setId(id);
        updateObj.setStatus(ORDER_STATUS_REFUND_REJECTED);
        courseOrderMapper.updateById(updateObj);
        // 通知用户退款申请被拒绝
        sendOrderStatusMessage(order, ORDER_STATUS_REFUND_REJECTED);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOrderStatus(Long id, Integer status) {
        CourseOrderDO order = courseOrderMapper.selectById(id);
        if (order == null) {
            throw exception(COURSE_ORDER_NOT_EXISTS);
        }
        CourseOrderDO updateObj = new CourseOrderDO();
        updateObj.setId(id);
        updateObj.setStatus(status);
        if (status != null && status == ORDER_STATUS_PAID) {
            updateObj.setPayTime(LocalDateTime.now());
        } else if (status != null && status == ORDER_STATUS_CANCELLED) {
            updateObj.setCancelTime(LocalDateTime.now());
        }
        courseOrderMapper.updateById(updateObj);
        // 状态变更后处理联动逻辑
        if (status != null) {
            if (status == ORDER_STATUS_PAID) {
                // 已支付：自动初始化学习记录（如不存在）
                initStudyRecord(order);
            }
            sendOrderStatusMessage(order, status);
        }
    }

    /**
     * 根据订单状态发送通知消息
     */
    private void sendOrderStatusMessage(CourseOrderDO order, Integer status) {
        String title = null;
        String content = null;
        if (status == ORDER_STATUS_PAID) {
            title = "课程报名成功";
            content = "您已成功报名课程《" + order.getCourseName() + "》，赶快开始学习吧！";
        } else if (status == ORDER_STATUS_CANCELLED) {
            title = "课程订单已取消";
            content = "您的课程《" + order.getCourseName() + "》订单已取消，订单号：" + order.getOrderNo();
        } else if (status == ORDER_STATUS_REFUNDED) {
            title = "课程退款成功";
            content = "您的课程《" + order.getCourseName() + "》退款已处理，订单号：" + order.getOrderNo();
        } else if (status == ORDER_STATUS_REFUND_REQUESTED) {
            title = "退款申请已提交";
            content = "您对课程《" + order.getCourseName() + "》的退款申请已提交，订单号：" + order.getOrderNo() + "，请等待管理员审核处理。";
        } else if (status == ORDER_STATUS_REFUND_REJECTED) {
            title = "退款申请被拒绝";
            content = "您对课程《" + order.getCourseName() + "》的退款申请已被拒绝，订单号：" + order.getOrderNo() + "。如有疑问请联系客服。";
        }
        if (title != null) {
            messageService.sendSystemMessage(order.getUserId(), title, content);
        }
    }

    /**
     * 初始化学习记录（订单支付后自动创建学习记录）
     */
    private void initStudyRecord(CourseOrderDO order) {
        StudyRecordDO existing = studyRecordMapper.selectByUserIdAndCourseId(order.getUserId(), order.getCourseId());
        if (existing == null) {
            StudyRecordDO record = new StudyRecordDO();
            record.setUserId(order.getUserId());
            record.setCourseId(order.getCourseId());
            record.setCourseName(order.getCourseName());
            record.setCoverUrl(order.getCoverUrl());
            record.setProgress(0);
            record.setLastStudyTime(LocalDateTime.now());
            record.setStatus(0);
            studyRecordMapper.insert(record);
        }
    }

    @Override
    public CourseOrderDO getOrder(Long id) {
        return courseOrderMapper.selectById(id);
    }

    @Override
    public PageResult<CourseOrderDO> getOrderPage(CourseOrderPageReqVO pageReqVO) {
        return courseOrderMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<CourseOrderDO> getMyOrderPage(AppCourseOrderPageReqVO pageReqVO, Long userId) {
        return courseOrderMapper.selectPageByUserId(pageReqVO, userId);
    }

    @Override
    public CourseOrderDO getOrderByUserIdAndCourseId(Long userId, Long courseId) {
        return courseOrderMapper.selectActiveByUserIdAndCourseId(userId, courseId);
    }

    @Override
    public void deleteOrder(Long id) {
        CourseOrderDO order = courseOrderMapper.selectById(id);
        if (order == null) {
            throw exception(COURSE_ORDER_NOT_EXISTS);
        }
        // 仅允许删除已取消（2）或已退款（3）状态的订单，防止误删有效订单
        if (order.getStatus() != ORDER_STATUS_CANCELLED && order.getStatus() != ORDER_STATUS_REFUNDED) {
            throw exception(COURSE_ORDER_CANNOT_DELETE);
        }
        courseOrderMapper.deleteById(id);
    }

    @Override
    public void deleteMyOrder(Long id, Long userId) {
        CourseOrderDO order = courseOrderMapper.selectById(id);
        if (order == null || !order.getUserId().equals(userId)) {
            throw exception(COURSE_ORDER_NOT_EXISTS);
        }
        // 仅允许删除已取消（2）或已退款（3）状态的订单
        if (order.getStatus() != ORDER_STATUS_CANCELLED && order.getStatus() != ORDER_STATUS_REFUNDED) {
            throw exception(COURSE_ORDER_CANNOT_DELETE);
        }
        courseOrderMapper.deleteById(id);
    }

}
