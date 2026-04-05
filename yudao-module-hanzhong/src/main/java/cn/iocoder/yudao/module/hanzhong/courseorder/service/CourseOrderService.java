package cn.iocoder.yudao.module.hanzhong.courseorder.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.courseorder.controller.admin.vo.CourseOrderPageReqVO;
import cn.iocoder.yudao.module.hanzhong.courseorder.controller.app.vo.AppCourseOrderCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.courseorder.controller.app.vo.AppCourseOrderPageReqVO;
import cn.iocoder.yudao.module.hanzhong.courseorder.dal.dataobject.CourseOrderDO;

/**
 * 汉中 课程订单 Service 接口
 *
 * @author hanzhong
 */
public interface CourseOrderService {

    /**
     * 创建订单
     */
    Long createOrder(Long userId, AppCourseOrderCreateReqVO createReqVO);

    /**
     * 取消订单
     */
    void cancelOrder(Long id, Long userId);

    /**
     * 更新订单状态（管理员操作，如手动标记已支付/退款等）
     */
    void updateOrderStatus(Long id, Integer status);

    /**
     * 获得订单
     */
    CourseOrderDO getOrder(Long id);

    /**
     * 获得订单分页（管理员）
     */
    PageResult<CourseOrderDO> getOrderPage(CourseOrderPageReqVO pageReqVO);

    /**
     * 获得我的订单分页
     */
    PageResult<CourseOrderDO> getMyOrderPage(AppCourseOrderPageReqVO pageReqVO, Long userId);

}
