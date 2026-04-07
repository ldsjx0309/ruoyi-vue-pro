package cn.iocoder.yudao.module.hanzhong.overview.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 管理后台 - 汉中 课程收入统计响应 VO
 *
 * @author hanzhong
 */
@Schema(description = "管理后台 - 汉中 课程收入统计响应 VO")
@Data
public class OverviewIncomeStatsRespVO {

    @Schema(description = "总收入（分）")
    private Long totalIncome;

    @Schema(description = "本月收入（分）")
    private Long currentMonthIncome;

    @Schema(description = "上月收入（分）")
    private Long lastMonthIncome;

    @Schema(description = "今日收入（分）")
    private Long todayIncome;

    @Schema(description = "最近 N 天日期列表（MM-dd 格式）")
    private List<String> dates;

    @Schema(description = "最近 N 天每日收入（分）")
    private List<Long> dailyIncome;

    @Schema(description = "最近 N 天每日订单数")
    private List<Long> dailyOrderCount;

    @Schema(description = "平均订单金额（分）")
    private Long avgOrderPrice;

}
