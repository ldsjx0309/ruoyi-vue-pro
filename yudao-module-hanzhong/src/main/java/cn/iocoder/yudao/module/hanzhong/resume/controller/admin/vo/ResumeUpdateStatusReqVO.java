package cn.iocoder.yudao.module.hanzhong.resume.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 汉中 简历状态更新 Request VO")
@Data
public class ResumeUpdateStatusReqVO {

    @Schema(description = "简历编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "简历编号不能为空")
    private Long id;

    @Schema(description = "状态（0-开启 1-关闭）", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    @NotNull(message = "状态不能为空")
    private Integer status;
}
