package cn.iocoder.yudao.module.hanzhong.job.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * 汉中 职位 更新 Request VO
 *
 * @author hanzhong
 */
@Schema(description = "管理后台 - 汉中 职位 更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class JobUpdateReqVO extends JobBaseVO {

    @Schema(description = "职位编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "职位编号不能为空")
    private Long id;

}
