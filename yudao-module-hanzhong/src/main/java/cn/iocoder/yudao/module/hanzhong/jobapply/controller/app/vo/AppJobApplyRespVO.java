package cn.iocoder.yudao.module.hanzhong.jobapply.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户 APP - 汉中 职位申请 响应 VO
 *
 * @author hanzhong
 */
@Schema(description = "用户 APP - 汉中 职位申请 Response VO")
@Data
public class AppJobApplyRespVO {

    @Schema(description = "编号", example = "1024")
    private Long id;

    @Schema(description = "职位编号", example = "1024")
    private Long jobId;

    @Schema(description = "职位名称（快照）")
    private String jobTitle;

    @Schema(description = "公司（快照）")
    private String company;

    @Schema(description = "使用的简历编号", example = "1024")
    private Long resumeId;

    @Schema(description = "状态（0-已投递 1-查看简历 2-邀请面试 3-不合适 4-已录用）", example = "0")
    private Integer status;

    @Schema(description = "HR 备注 / 反馈意见")
    private String remark;

    @Schema(description = "申请时间")
    private LocalDateTime applyTime;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
