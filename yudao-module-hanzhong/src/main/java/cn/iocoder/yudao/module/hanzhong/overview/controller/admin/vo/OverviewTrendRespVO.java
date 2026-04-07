package cn.iocoder.yudao.module.hanzhong.overview.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 管理后台 - 汉中 趋势统计 Response VO
 *
 * @author hanzhong
 */
@Schema(description = "管理后台 - 汉中 趋势统计 Response VO")
@Data
public class OverviewTrendRespVO {

    @Schema(description = "日期标签列表（格式：MM-dd）")
    private List<String> dates;

    @Schema(description = "每日新注册用户数")
    private List<Long> newProfiles;

    @Schema(description = "每日新课程订单数")
    private List<Long> newCourseOrders;

    @Schema(description = "每日新职位申请数")
    private List<Long> newJobApplies;

    @Schema(description = "每日新社区帖子数")
    private List<Long> newPosts;

    @Schema(description = "每日新学习记录数（新增课程学习）")
    private List<Long> newStudyRecords;

    @Schema(description = "每日新职位收藏数")
    private List<Long> newJobCollects;

    @Schema(description = "每日新课程评分数")
    private List<Long> newCourseRatings;

}
