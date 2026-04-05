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

/**
 * 汉中 课程订单 Service 实现类
 *
 * @author hanzhong
 */
@Service
@Validated
public class CourseOrderServiceImpl implements CourseOrderService {

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
        order.setStatus(0);
        order.setOrderNo(generateOrderNo());
        courseOrderMapper.insert(order);
        // 增加课程报名人数
        courseService.incrementEnrollCount(course.getId());
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
        updateObj.setStatus(2);
        updateObj.setCancelTime(LocalDateTime.now());
        courseOrderMapper.updateById(updateObj);
        // 发送取消通知
        sendOrderStatusMessage(order, 2);
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
        if (status != null && status == 1) {
            updateObj.setPayTime(LocalDateTime.now());
        } else if (status != null && status == 2) {
            updateObj.setCancelTime(LocalDateTime.now());
        }
        courseOrderMapper.updateById(updateObj);
        // 状态变更后处理联动逻辑
        if (status != null) {
            if (status == 1) {
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
        if (status == 1) {
            title = "课程报名成功";
            content = "您已成功报名课程《" + order.getCourseName() + "》，赶快开始学习吧！";
        } else if (status == 2) {
            title = "课程订单已取消";
            content = "您的课程《" + order.getCourseName() + "》订单已取消，订单号：" + order.getOrderNo();
        } else if (status == 3) {
            title = "课程退款成功";
            content = "您的课程《" + order.getCourseName() + "》退款已处理，订单号：" + order.getOrderNo();
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

}
