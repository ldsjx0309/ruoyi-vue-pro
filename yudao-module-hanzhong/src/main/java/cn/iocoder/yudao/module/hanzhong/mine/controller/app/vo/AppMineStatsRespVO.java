package cn.iocoder.yudao.module.hanzhong.mine.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户 APP - 汉中 我的统计 响应 VO
 *
 * @author hanzhong
 */
@Schema(description = "用户 APP - 汉中 我的统计 Response VO")
@Data
public class AppMineStatsRespVO {

    @Schema(description = "课程订单总数", example = "5")
    private Long totalCourseOrders;

    @Schema(description = "学习中课程数（已支付订单数）", example = "4")
    private Long activeCourses;

    @Schema(description = "职位申请总数", example = "3")
    private Long totalJobApplies;

    @Schema(description = "我的帖子总数", example = "7")
    private Long totalPosts;

    @Schema(description = "名片交换记录总数", example = "2")
    private Long totalCardExchanges;

    @Schema(description = "未读消息数", example = "6")
    private Long unreadMessages;

}
