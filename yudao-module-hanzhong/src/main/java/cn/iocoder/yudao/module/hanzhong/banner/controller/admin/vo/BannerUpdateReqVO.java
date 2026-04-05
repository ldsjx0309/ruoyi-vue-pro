package cn.iocoder.yudao.module.hanzhong.banner.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * 汉中 Banner 更新 Request VO
 *
 * @author hanzhong
 */
@Schema(description = "管理后台 - 汉中 Banner 更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BannerUpdateReqVO extends BannerBaseVO {

    @Schema(description = "Banner 编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "Banner 编号不能为空")
    private Long id;

}
