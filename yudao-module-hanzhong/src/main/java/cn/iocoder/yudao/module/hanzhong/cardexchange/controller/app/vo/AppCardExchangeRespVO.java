package cn.iocoder.yudao.module.hanzhong.cardexchange.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户 APP - 汉中 名片交换 响应 VO
 *
 * @author hanzhong
 */
@Schema(description = "用户 APP - 汉中 名片交换 Response VO")
@Data
public class AppCardExchangeRespVO {

    @Schema(description = "编号", example = "1024")
    private Long id;

    @Schema(description = "被查看名片的用户编号", example = "2048")
    private Long targetUserId;

    @Schema(description = "名片记录编号", example = "1024")
    private Long targetCardId;

    @Schema(description = "名片姓名（快照）")
    private String targetName;

    @Schema(description = "名片公司（快照）")
    private String targetCompany;

    @Schema(description = "交换时间")
    private LocalDateTime exchangeTime;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
