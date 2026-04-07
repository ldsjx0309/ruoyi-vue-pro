package cn.iocoder.yudao.module.hanzhong.overview.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 管理后台 - 汉中 待处理任务数 响应 VO（用于管理员仪表盘徽标/角标）
 *
 * @author hanzhong
 */
@Schema(description = "管理后台 - 汉中 待处理任务数 Response VO")
@Data
public class OverviewPendingTasksRespVO {

    @Schema(description = "待处理职位申请数（状态=0 已投递，尚未 HR 审核）", example = "12")
    private Long pendingJobApplies;

    @Schema(description = "待审批退款申请订单数（状态=4 退款申请中）", example = "3")
    private Long pendingRefundOrders;

    @Schema(description = "待处理任务总数（所有待处理项之和，可用于顶部角标）", example = "15")
    private Long totalPending;

}
