package cn.iocoder.yudao.module.hanzhong.coursesection.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "用户 APP - 课程章节 Response VO")
@Data
public class AppCourseSectionRespVO {

    @Schema(description = "编号", example = "1024")
    private Long id;

    @Schema(description = "所属课程编号", example = "1")
    private Long courseId;

    @Schema(description = "章节标题", example = "第一章 入门介绍")
    private String title;

    @Schema(description = "章节描述", example = "本章介绍课程基础内容")
    private String description;

    @Schema(description = "视频地址", example = "https://example.com/video.mp4")
    private String videoUrl;

    @Schema(description = "视频时长（秒）", example = "600")
    private Integer duration;

    @Schema(description = "排序", example = "1")
    private Integer sort;

    @Schema(description = "是否免费试看（0-否 1-是）", example = "0")
    private Integer freePreview;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
