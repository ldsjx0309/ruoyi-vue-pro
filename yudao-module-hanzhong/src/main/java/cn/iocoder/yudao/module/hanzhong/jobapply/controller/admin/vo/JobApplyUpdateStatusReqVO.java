package cn.iocoder.yudao.module.hanzhong.jobapply.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 汉中 职位申请 状态更新 Request VO
 *
 * @author hanzhong
 */
@Schema(description = "管理后台 - 汉中 职位申请 状态更新 Request VO")
@Data
public class JobApplyUpdateStatusReqVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "编号不能为空")
    private Long id;

    @Schema(description = "状态（0-已投递 1-查看简历 2-邀请面试 3-不合适 4-已录用）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "状态不能为空")
    private Integer status;

}
