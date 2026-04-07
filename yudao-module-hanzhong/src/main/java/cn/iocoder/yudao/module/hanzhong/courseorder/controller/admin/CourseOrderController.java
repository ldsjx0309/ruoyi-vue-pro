package cn.iocoder.yudao.module.hanzhong.courseorder.controller.admin;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.courseorder.controller.admin.vo.CourseOrderPageReqVO;
import cn.iocoder.yudao.module.hanzhong.courseorder.controller.admin.vo.CourseOrderRespVO;
import cn.iocoder.yudao.module.hanzhong.courseorder.controller.admin.vo.CourseOrderStatusStatsRespVO;
import cn.iocoder.yudao.module.hanzhong.courseorder.controller.admin.vo.CourseOrderUpdateStatusReqVO;
import cn.iocoder.yudao.module.hanzhong.courseorder.convert.CourseOrderConvert;
import cn.iocoder.yudao.module.hanzhong.courseorder.dal.dataobject.CourseOrderDO;
import cn.iocoder.yudao.module.hanzhong.courseorder.dal.mysql.CourseOrderMapper;
import cn.iocoder.yudao.module.hanzhong.courseorder.service.CourseOrderService;
import cn.iocoder.yudao.module.hanzhong.util.CsvUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

    @Resource
    private CourseOrderMapper courseOrderMapper;

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

    @GetMapping("/stats")
    @Operation(summary = "获得课程订单状态分布统计（用于订单看板视图）")
    @PreAuthorize("@ss.hasPermission('hanzhong:course-order:query')")
    public CommonResult<CourseOrderStatusStatsRespVO> getOrderStats() {
        CourseOrderStatusStatsRespVO respVO = new CourseOrderStatusStatsRespVO();
        java.util.List<java.util.Map<String, Object>> rows = courseOrderMapper.selectCountGroupByStatus();
        java.util.Map<Integer, Long> countMap = new java.util.HashMap<>();
        long total = 0L;
        for (java.util.Map<String, Object> row : rows) {
            Integer status = row.get("status") != null ? ((Number) row.get("status")).intValue() : null;
            Long cnt = row.get("cnt") != null ? ((Number) row.get("cnt")).longValue() : 0L;
            if (status != null) {
                countMap.put(status, cnt);
                total += cnt;
            }
        }
        respVO.setPending(countMap.getOrDefault(0, 0L));
        respVO.setPaid(countMap.getOrDefault(1, 0L));
        respVO.setCancelled(countMap.getOrDefault(2, 0L));
        respVO.setRefunded(countMap.getOrDefault(3, 0L));
        respVO.setRefundRequested(countMap.getOrDefault(4, 0L));
        respVO.setRefundRejected(countMap.getOrDefault(5, 0L));
        respVO.setTotal(total);
        return success(respVO);
    }

    @GetMapping("/export")
    @Operation(summary = "导出课程订单列表（CSV）")
    @PreAuthorize("@ss.hasPermission('hanzhong:course-order:query')")
    public void exportCourseOrder(@Valid CourseOrderPageReqVO pageVO, HttpServletResponse response) throws IOException {
        response.setContentType("text/csv; charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=course-order-export.csv");
        java.io.OutputStream os = response.getOutputStream();
        // BOM for Excel UTF-8 recognition — flush before wrapping to avoid buffering order issues
        os.write(new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF});
        os.flush();
        PrintWriter writer = new PrintWriter(new java.io.BufferedWriter(
                new java.io.OutputStreamWriter(os, java.nio.charset.StandardCharsets.UTF_8)));
        writer.println(String.join(",",
                CsvUtils.escapeCsv("编号"), CsvUtils.escapeCsv("订单编号"), CsvUtils.escapeCsv("用户编号"),
                CsvUtils.escapeCsv("课程编号"), CsvUtils.escapeCsv("课程名称"), CsvUtils.escapeCsv("实付价格（分）"),
                CsvUtils.escapeCsv("状态"), CsvUtils.escapeCsv("支付时间"), CsvUtils.escapeCsv("取消时间"),
                CsvUtils.escapeCsv("备注"), CsvUtils.escapeCsv("创建时间")));
        java.time.LocalDateTime[] createTime = pageVO.getCreateTime();
        boolean hasStart = createTime != null && createTime.length > 0 && createTime[0] != null;
        boolean hasEnd = createTime != null && createTime.length > 1 && createTime[1] != null;
        LambdaQueryWrapper<CourseOrderDO> wrapper = new LambdaQueryWrapper<CourseOrderDO>()
                .eq(pageVO.getUserId() != null, CourseOrderDO::getUserId, pageVO.getUserId())
                .eq(pageVO.getCourseId() != null, CourseOrderDO::getCourseId, pageVO.getCourseId())
                .eq(pageVO.getStatus() != null, CourseOrderDO::getStatus, pageVO.getStatus())
                .ge(hasStart, CourseOrderDO::getCreateTime, hasStart ? createTime[0] : null)
                .le(hasEnd, CourseOrderDO::getCreateTime, hasEnd ? createTime[1] : null)
                .orderByDesc(CourseOrderDO::getCreateTime);
        List<CourseOrderDO> list = courseOrderMapper.selectList(wrapper);
        for (CourseOrderDO item : list) {
            writer.println(String.join(",",
                    CsvUtils.str(item.getId()),
                    CsvUtils.escapeCsv(item.getOrderNo()),
                    CsvUtils.str(item.getUserId()),
                    CsvUtils.str(item.getCourseId()),
                    CsvUtils.escapeCsv(item.getCourseName()),
                    CsvUtils.str(item.getPrice()),
                    CsvUtils.str(item.getStatus()),
                    CsvUtils.str(item.getPayTime()),
                    CsvUtils.str(item.getCancelTime()),
                    CsvUtils.escapeCsv(item.getRemark()),
                    CsvUtils.str(item.getCreateTime())));
        }
        writer.flush();
    }

}
