package cn.iocoder.yudao.module.hanzhong.jobapply.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 汉中 职位申请 Base VO
 *
 * @author hanzhong
 */
@Data
public class JobApplyBaseVO {

    @Schema(description = "用户编号", example = "1024")
    private Long userId;

    @Schema(description = "职位编号", example = "1024")
    private Long jobId;

    @Schema(description = "使用的简历编号", example = "1024")
    private Long resumeId;

    @Schema(description = "状态（0-已投递 1-查看简历 2-邀请面试 3-不合适 4-已录用）", example = "0")
    private Integer status;

    @Schema(description = "备注")
    private String remark;

}
