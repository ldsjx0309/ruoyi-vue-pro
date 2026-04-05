package cn.iocoder.yudao.module.hanzhong.cardexchange.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 汉中 名片交换 响应 VO
 *
 * @author hanzhong
 */
@Schema(description = "管理后台 - 汉中 名片交换 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CardExchangeRespVO extends CardExchangeBaseVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    @Schema(description = "名片姓名（快照）")
    private String targetName;

    @Schema(description = "名片公司（快照）")
    private String targetCompany;

    @Schema(description = "交换时间")
    private LocalDateTime exchangeTime;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
