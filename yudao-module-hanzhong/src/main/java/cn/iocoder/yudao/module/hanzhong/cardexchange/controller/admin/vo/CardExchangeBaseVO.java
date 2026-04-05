package cn.iocoder.yudao.module.hanzhong.cardexchange.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 汉中 名片交换 Base VO
 *
 * @author hanzhong
 */
@Data
public class CardExchangeBaseVO {

    @Schema(description = "发起人用户编号", example = "1024")
    private Long userId;

    @Schema(description = "被查看名片的用户编号", example = "2048")
    private Long targetUserId;

    @Schema(description = "名片记录编号", example = "1024")
    private Long targetCardId;

    @Schema(description = "备注")
    private String remark;

}
