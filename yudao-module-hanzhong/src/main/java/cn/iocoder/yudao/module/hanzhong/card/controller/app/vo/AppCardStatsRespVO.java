package cn.iocoder.yudao.module.hanzhong.card.controller.app.vo;

import cn.iocoder.yudao.module.hanzhong.cardexchange.controller.app.vo.AppCardExchangeRespVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "用户 APP - 汉中 名片夹统计 Response VO")
@Data
public class AppCardStatsRespVO {

    @Schema(description = "本月交换数", example = "6")
    private Long monthExchangeCount;

    @Schema(description = "累计交换数", example = "18")
    private Long totalExchangeCount;

    @Schema(description = "最近交换记录")
    private List<AppCardExchangeRespVO> recentExchanges;
}
