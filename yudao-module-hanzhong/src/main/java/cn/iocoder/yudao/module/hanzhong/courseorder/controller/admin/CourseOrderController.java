package cn.iocoder.yudao.module.hanzhong.courseorder.controller.admin;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.courseorder.controller.admin.vo.CourseOrderPageReqVO;
import cn.iocoder.yudao.module.hanzhong.courseorder.controller.admin.vo.CourseOrderRespVO;
import cn.iocoder.yudao.module.hanzhong.courseorder.controller.admin.vo.CourseOrderUpdateStatusReqVO;
import cn.iocoder.yudao.module.hanzhong.courseorder.convert.CourseOrderConvert;
import cn.iocoder.yudao.module.hanzhong.courseorder.dal.dataobject.CourseOrderDO;
import cn.iocoder.yudao.module.hanzhong.courseorder.service.CourseOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 管理后台 - 汉中 课程订单管理
 *
 * @author hanzhong
 */
@Tag(name = "管理后台 - 汉中 课程订单管理")
@RestController
@RequestMapping("/hanzhong/course-order")
@Validated
public class CourseOrderController {

    @Resource
    private CourseOrderService courseOrderService;

    @PutMapping("/update-status")
    @Operation(summary = "更新课程订单状态")
    @PreAuthorize("@ss.hasPermission('hanzhong:course-order:update')")
    public CommonResult<Boolean> updateOrderStatus(@Valid @RequestBody CourseOrderUpdateStatusReqVO updateReqVO) {
        courseOrderService.updateOrderStatus(updateReqVO.getId(), updateReqVO.getStatus());
        return success(true);
    }

    @PutMapping("/refund")
    @Operation(summary = "课程订单退款（将订单状态更新为已退款）")
    @Parameter(name = "id", description = "订单编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hanzhong:course-order:update')")
    public CommonResult<Boolean> refundOrder(@RequestParam("id") Long id) {
        courseOrderService.updateOrderStatus(id, 3);
        return success(true);
    }

    @PutMapping("/approve-refund")
    @Operation(summary = "审批通过退款申请（仅退款申请中的订单可操作，通过后状态变为已退款并通知用户）")
    @Parameter(name = "id", description = "订单编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hanzhong:course-order:update')")
    public CommonResult<Boolean> approveRefund(@RequestParam("id") Long id) {
        courseOrderService.approveRefund(id);
        return success(true);
    }

    @PutMapping("/reject-refund")
    @Operation(summary = "拒绝退款申请（仅退款申请中的订单可操作，拒绝后状态变为退款拒绝并通知用户）")
    @Parameter(name = "id", description = "订单编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hanzhong:course-order:update')")
    public CommonResult<Boolean> rejectRefund(@RequestParam("id") Long id) {
        courseOrderService.rejectRefund(id);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除课程订单（管理员）")
    @Parameter(name = "id", description = "订单编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hanzhong:course-order:delete')")
    public CommonResult<Boolean> deleteOrder(@RequestParam("id") Long id) {
        courseOrderService.deleteOrder(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得课程订单详情")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hanzhong:course-order:query')")
    public CommonResult<CourseOrderRespVO> getOrder(@RequestParam("id") Long id) {
        CourseOrderDO order = courseOrderService.getOrder(id);
        return success(CourseOrderConvert.INSTANCE.convert(order));
    }

    @GetMapping("/page")
    @Operation(summary = "获得课程订单分页")
    @PreAuthorize("@ss.hasPermission('hanzhong:course-order:query')")
    public CommonResult<PageResult<CourseOrderRespVO>> getOrderPage(@Valid CourseOrderPageReqVO pageVO) {
        PageResult<CourseOrderDO> pageResult = courseOrderService.getOrderPage(pageVO);
        return success(CourseOrderConvert.INSTANCE.convertPage(pageResult));
    }

}
