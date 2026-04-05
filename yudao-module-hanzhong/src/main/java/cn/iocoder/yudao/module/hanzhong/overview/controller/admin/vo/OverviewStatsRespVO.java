package cn.iocoder.yudao.module.hanzhong.overview.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 管理后台 - 汉中 概览统计 响应 VO
 *
 * @author hanzhong
 */
@Schema(description = "管理后台 - 汉中 概览统计 Response VO")
@Data
public class OverviewStatsRespVO {

    @Schema(description = "注册用户档案总数", example = "128")
    private Long totalUserProfiles;

    @Schema(description = "课程总数", example = "32")
    private Long totalCourses;

    @Schema(description = "职位总数", example = "18")
    private Long totalJobs;

    @Schema(description = "课程订单总数", example = "256")
    private Long totalCourseOrders;

    @Schema(description = "已支付课程订单数", example = "210")
    private Long paidCourseOrders;

    @Schema(description = "职位申请总数", example = "97")
    private Long totalJobApplies;

    @Schema(description = "社区帖子总数", example = "45")
    private Long totalCommunityPosts;

    @Schema(description = "名片总数", example = "60")
    private Long totalCards;

    @Schema(description = "名片交换记录总数", example = "38")
    private Long totalCardExchanges;

    @Schema(description = "消息总数", example = "512")
    private Long totalMessages;

    @Schema(description = "未读消息总数", example = "80")
    private Long unreadMessages;

    @Schema(description = "学习记录总数", example = "180")
    private Long totalStudyRecords;

    @Schema(description = "已完成学习记录数（进度 100%）", example = "45")
    private Long completedStudyRecords;

    @Schema(description = "课程收藏总数", example = "130")
    private Long totalCourseFavorites;

    @Schema(description = "帖子评论总数", example = "320")
    private Long totalPostComments;

}
