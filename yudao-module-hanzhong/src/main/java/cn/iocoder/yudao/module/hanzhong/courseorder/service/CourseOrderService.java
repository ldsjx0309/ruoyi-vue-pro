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
     * 用户确认支付（模拟支付；免费课程下单后直接调用）
     *
     * @param id     订单编号
     * @param userId 当前登录用户编号
     */
    void payOrder(Long id, Long userId);

    /**
     * 创建订单
     */
    Long createOrder(Long userId, AppCourseOrderCreateReqVO createReqVO);

    /**
     * 取消订单
     */
    void cancelOrder(Long id, Long userId);

    /**
     * 用户申请退款（仅已支付订单可申请）
     * 将订单状态由 1-已支付 变更为 4-退款申请中，等待管理员审核处理。
     *
     * @param id     订单编号
     * @param userId 当前登录用户编号
     */
    void requestRefund(Long id, Long userId);

    /**
     * 管理员审批通过退款申请
     * 将订单状态由 4-退款申请中 变更为 3-已退款，同时通知用户。
     * 与 {@link #updateOrderStatus} 不同，此方法会严格校验订单必须处于退款申请中状态。
     *
     * @param id 订单编号
     */
    void approveRefund(Long id);

    /**
     * 管理员拒绝退款申请
     * 将订单状态由 4-退款申请中 变更为 5-退款拒绝，同时通知用户。
     *
     * @param id 订单编号
     */
    void rejectRefund(Long id);

    /**
     * 删除订单（管理员操作，仅限已取消或已退款状态的订单）
     *
     * @param id 订单编号
     */
    void deleteOrder(Long id);

    /**
     * 用户删除自己的订单（仅限已取消或已退款状态的订单）
     *
     * @param id     订单编号
     * @param userId 当前登录用户编号
     */
    void deleteMyOrder(Long id, Long userId);

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

    /**
     * 根据用户编号和课程编号获得订单（有效订单，用于检查购买状态）
     *
     * @param userId   用户编号
     * @param courseId 课程编号
     * @return 订单，无则返回 null
     */
    CourseOrderDO getOrderByUserIdAndCourseId(Long userId, Long courseId);

}
