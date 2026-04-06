package cn.iocoder.yudao.module.hanzhong.course.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户 APP - 汉中 课程 响应 VO
 *
 * @author hanzhong
 */
@Schema(description = "用户 APP - 汉中 课程 Response VO")
@Data
public class AppCourseRespVO {

    @Schema(description = "课程编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    @Schema(description = "分类编号", example = "1")
    private Long categoryId;

    @Schema(description = "课程标题", requiredMode = Schema.RequiredMode.REQUIRED, example = "Java 零基础入门")
    private String title;

    @Schema(description = "封面图片地址")
    private String coverUrl;

    @Schema(description = "简介")
    private String summary;

    @Schema(description = "课程内容")
    private String content;

    @Schema(description = "讲师名称")
    private String teacherName;

    @Schema(description = "价格（分）", example = "0")
    private Integer price;

    @Schema(description = "原价（分）", example = "9900")
    private Integer originalPrice;

    @Schema(description = "浏览量", example = "100")
    private Integer viewCount;

    @Schema(description = "报名人数", example = "50")
    private Integer enrollCount;

    @Schema(description = "排序", example = "1")
    private Integer sort;

    @Schema(description = "当前登录用户是否已购买（匿名用户为 null）")
    private Boolean hasPurchased;

    @Schema(description = "当前登录用户是否已收藏（匿名用户为 null）")
    private Boolean isFavorited;

    @Schema(description = "章节数（仅课程列表场景中设置）", example = "10")
    private Integer sectionCount;

}
