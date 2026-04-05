package cn.iocoder.yudao.module.hanzhong.courseorder.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.course.dal.dataobject.CourseDO;
import cn.iocoder.yudao.module.hanzhong.course.dal.mysql.CourseMapper;
import cn.iocoder.yudao.module.hanzhong.courseorder.controller.admin.vo.CourseOrderPageReqVO;
import cn.iocoder.yudao.module.hanzhong.courseorder.controller.app.vo.AppCourseOrderCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.courseorder.controller.app.vo.AppCourseOrderPageReqVO;
import cn.iocoder.yudao.module.hanzhong.courseorder.dal.dataobject.CourseOrderDO;
import cn.iocoder.yudao.module.hanzhong.courseorder.dal.mysql.CourseOrderMapper;
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
    }

    @Override
    public void updateOrderStatus(Long id, Integer status) {
        if (courseOrderMapper.selectById(id) == null) {
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
