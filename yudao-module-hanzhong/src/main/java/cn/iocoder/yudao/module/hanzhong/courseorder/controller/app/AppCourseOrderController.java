package cn.iocoder.yudao.module.hanzhong.courseorder.controller.app;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.hanzhong.courseorder.controller.app.vo.AppCourseOrderCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.courseorder.controller.app.vo.AppCourseOrderPageReqVO;
import cn.iocoder.yudao.module.hanzhong.courseorder.controller.app.vo.AppCourseOrderRespVO;
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
 * 用户 APP - 汉中 课程订单
 *
 * @author hanzhong
 */
@RestController
@RequestMapping("/hanzhong/app/course-order")
@Tag(name = "用户 APP - 汉中 课程订单")
@Validated
public class AppCourseOrderController {

    @Resource
    private CourseOrderService courseOrderService;

    @GetMapping("/page")
    @Operation(summary = "获取我的课程订单分页")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<PageResult<AppCourseOrderRespVO>> getMyOrderPage(@Valid AppCourseOrderPageReqVO pageReqVO) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        PageResult<CourseOrderDO> pageResult = courseOrderService.getMyOrderPage(pageReqVO, userId);
        return success(CourseOrderConvert.INSTANCE.convertAppPage(pageResult));
    }

    @GetMapping("/get")
    @Operation(summary = "获取课程订单详情")
    @Parameter(name = "id", description = "订单编号", required = true, example = "1024")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<AppCourseOrderRespVO> getOrder(@RequestParam("id") Long id) {
        CourseOrderDO order = courseOrderService.getOrder(id);
        return success(CourseOrderConvert.INSTANCE.convertApp(order));
    }

    @PostMapping("/create")
    @Operation(summary = "创建课程订单")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Long> createOrder(@Valid @RequestBody AppCourseOrderCreateReqVO createReqVO) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        return success(courseOrderService.createOrder(userId, createReqVO));
    }

    @PutMapping("/pay")
    @Operation(summary = "确认支付课程订单（付费课程模拟支付/免费课程无需调用）")
    @Parameter(name = "id", description = "订单编号", required = true, example = "1024")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Boolean> payOrder(@RequestParam("id") Long id) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        courseOrderService.payOrder(id, userId);
        return success(true);
    }

    @PutMapping("/cancel")
    @Operation(summary = "取消课程订单")
    @Parameter(name = "id", description = "订单编号", required = true, example = "1024")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Boolean> cancelOrder(@RequestParam("id") Long id) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        courseOrderService.cancelOrder(id, userId);
        return success(true);
    }

}
