package cn.iocoder.yudao.module.hanzhong.cardexchange.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 用户 APP - 汉中 名片交换 创建 Request VO
 *
 * @author hanzhong
 */
@Schema(description = "用户 APP - 汉中 名片交换 创建 Request VO")
@Data
public class AppCardExchangeCreateReqVO {

    @Schema(description = "被查看名片的用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "2048")
    @NotNull(message = "目标用户编号不能为空")
    private Long targetUserId;

    @Schema(description = "名片记录编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "名片编号不能为空")
    private Long targetCardId;

}
