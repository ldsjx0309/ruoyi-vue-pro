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
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Random;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
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
    public Long createOrder(Long userId, AppCourseOrderCreateReqVO createReqVO) {
        CourseDO course = courseMapper.selectById(createReqVO.getCourseId());
        if (course == null) {
            throw exception(COURSE_NOT_EXISTS);
        }
        CourseOrderDO order = new CourseOrderDO();
        order.setUserId(userId);
        order.setCourseId(course.getId());
        order.setCourseName(course.getTitle());
        order.setCoverUrl(course.getCoverUrl());
        order.setPrice(course.getPrice());
        order.setStatus(0);
        order.setOrderNo(System.currentTimeMillis() + String.format("%04d", new Random().nextInt(10000)));
        courseOrderMapper.insert(order);
        return order.getId();
    }

    @Override
    public void cancelOrder(Long id, Long userId) {
        CourseOrderDO order = courseOrderMapper.selectById(id);
        if (order == null) {
            throw exception(COURSE_ORDER_NOT_EXISTS);
        }
        CourseOrderDO updateObj = new CourseOrderDO();
        updateObj.setId(id);
        updateObj.setStatus(2);
        updateObj.setCancelTime(LocalDateTime.now());
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
