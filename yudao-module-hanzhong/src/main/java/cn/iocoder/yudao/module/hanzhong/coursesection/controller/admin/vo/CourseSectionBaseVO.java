package cn.iocoder.yudao.module.hanzhong.coursesection.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 管理后台 - 课程章节基础 VO
 */
@Data
public class CourseSectionBaseVO {

    @Schema(description = "所属课程编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "课程编号不能为空")
    private Long courseId;

    @Schema(description = "章节标题", requiredMode = Schema.RequiredMode.REQUIRED, example = "第一章 入门介绍")
    @NotBlank(message = "章节标题不能为空")
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

    @Schema(description = "状态（0-开启 1-关闭）", example = "0")
    private Integer status;

    @Schema(description = "备注", example = "")
    private String remark;

}
