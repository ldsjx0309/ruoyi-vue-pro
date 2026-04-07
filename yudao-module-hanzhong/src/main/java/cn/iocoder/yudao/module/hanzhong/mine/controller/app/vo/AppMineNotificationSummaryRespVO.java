package cn.iocoder.yudao.module.hanzhong.mine.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户 APP - 汉中 通知概要 响应 VO（用于小程序角标）
 *
 * @author hanzhong
 */
@Schema(description = "用户 APP - 汉中 通知概要 Response VO（用于角标统计）")
@Data
public class AppMineNotificationSummaryRespVO {

    @Schema(description = "未读消息数", example = "3")
    private Long unreadMessages;

    @Schema(description = "有新进展的职位申请数（状态非 0-已投递 的申请数，包含面试邀约、录用、拒绝等）", example = "2")
    private Long updatedJobApplies;

    @Schema(description = "退款申请处理结果数（退款已完成或退款被拒绝但用户未查看，当前版本统一返回 0）", example = "0")
    private Long processedRefunds;

    @Schema(description = "通知总数（所有未处理通知之和，可直接用于 tabBar 角标）", example = "5")
    private Long totalNotifications;

}
