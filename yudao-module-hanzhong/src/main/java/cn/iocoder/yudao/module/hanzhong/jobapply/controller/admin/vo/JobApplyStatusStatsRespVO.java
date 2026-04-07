package cn.iocoder.yudao.module.hanzhong.jobapply.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 管理后台 - 汉中 职位申请按状态统计 响应 VO（用于申请看板视图）
 *
 * @author hanzhong
 */
@Schema(description = "管理后台 - 汉中 职位申请状态分布统计 Response VO")
@Data
public class JobApplyStatusStatsRespVO {

    @Schema(description = "已投递（状态=0，待 HR 审核）", example = "12")
    private Long submitted;

    @Schema(description = "查看简历（状态=1）", example = "8")
    private Long viewed;

    @Schema(description = "邀请面试（状态=2）", example = "5")
    private Long invited;

    @Schema(description = "不合适（状态=3）", example = "6")
    private Long rejected;

    @Schema(description = "已录用（状态=4）", example = "3")
    private Long hired;

    @Schema(description = "合计", example = "34")
    private Long total;

}
