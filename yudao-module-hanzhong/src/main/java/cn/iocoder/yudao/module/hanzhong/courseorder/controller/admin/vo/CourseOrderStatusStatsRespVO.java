package cn.iocoder.yudao.module.hanzhong.courseorder.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 管理后台 - 汉中 课程订单状态分布统计响应 VO
 *
 * @author hanzhong
 */
@Schema(description = "管理后台 - 汉中 课程订单状态分布统计响应 VO")
@Data
public class CourseOrderStatusStatsRespVO {

    @Schema(description = "待支付订单数")
    private Long pending;

    @Schema(description = "已支付订单数")
    private Long paid;

    @Schema(description = "已取消订单数")
    private Long cancelled;

    @Schema(description = "已退款订单数")
    private Long refunded;

    @Schema(description = "退款申请中订单数")
    private Long refundRequested;

    @Schema(description = "退款拒绝订单数")
    private Long refundRejected;

    @Schema(description = "订单总数")
    private Long total;

}
