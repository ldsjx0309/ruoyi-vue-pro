package cn.iocoder.yudao.module.hanzhong.jobapply.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 用户 APP - 汉中 职位申请 创建 Request VO
 *
 * @author hanzhong
 */
@Schema(description = "用户 APP - 汉中 职位申请 创建 Request VO")
@Data
public class AppJobApplyCreateReqVO {

    @Schema(description = "职位编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "职位编号不能为空")
    private Long jobId;

    @Schema(description = "使用的简历编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "简历编号不能为空")
    private Long resumeId;

}
