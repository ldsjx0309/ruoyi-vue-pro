package cn.iocoder.yudao.module.hanzhong.course.controller.app.vo;

import cn.iocoder.yudao.module.hanzhong.coursesection.controller.app.vo.AppCourseSectionRespVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 用户 APP - 汉中 课程详情（含章节列表）响应 VO
 *
 * @author hanzhong
 */
@Schema(description = "用户 APP - 汉中 课程详情 Response VO（含章节列表）")
@Data
@EqualsAndHashCode(callSuper = true)
public class AppCourseDetailVO extends AppCourseRespVO {

    @Schema(description = "章节列表")
    private List<AppCourseSectionRespVO> sections;

    @Schema(description = "章节总数", example = "10")
    private Integer sectionCount;

    @Schema(description = "总时长（秒，所有章节之和）", example = "3600")
    private Integer totalDuration;

    @Schema(description = "平均评分（1-5，保留一位小数；无评分时为 0.0）", example = "4.7")
    private Double averageRating;

    @Schema(description = "评分人数", example = "128")
    private Integer ratingCount;

    @Schema(description = "当前登录用户对本课程的评分（未评分为 null）", example = "5")
    private Integer myRating;

}
