package cn.iocoder.yudao.module.hanzhong.overview.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 管理后台 - 汉中 用户活动统计 响应 VO
 *
 * @author hanzhong
 */
@Schema(description = "管理后台 - 汉中 用户活动统计 Response VO")
@Data
public class UserActivityRespVO {

    @Schema(description = "用户编号", example = "1024")
    private Long userId;

    @Schema(description = "课程订单总数", example = "3")
    private Long totalCourseOrders;

    @Schema(description = "已支付课程数", example = "2")
    private Long paidCourses;

    @Schema(description = "已完成课程数", example = "1")
    private Long completedCourses;

    @Schema(description = "职位申请总数", example = "5")
    private Long totalJobApplies;

    @Schema(description = "社区帖子数", example = "4")
    private Long totalPosts;

    @Schema(description = "名片交换记录总数", example = "7")
    private Long totalCardExchanges;

    @Schema(description = "消息总数", example = "12")
    private Long totalMessages;

    @Schema(description = "未读消息数", example = "3")
    private Long unreadMessages;

    @Schema(description = "课程收藏数", example = "6")
    private Long totalCourseFavorites;

    @Schema(description = "帖子评论数", example = "9")
    private Long totalPostComments;

}
