package cn.iocoder.yudao.module.hanzhong.studyrecord.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户 APP - 汉中 学习记录 响应 VO
 *
 * @author hanzhong
 */
@Schema(description = "用户 APP - 汉中 学习记录 Response VO")
@Data
public class AppStudyRecordRespVO {

    @Schema(description = "编号", example = "1024")
    private Long id;

    @Schema(description = "课程编号", example = "1024")
    private Long courseId;

    @Schema(description = "课程名称（快照）")
    private String courseName;

    @Schema(description = "课程封面（快照）")
    private String coverUrl;

    @Schema(description = "最后学习的章节编号（用于断点续播，可为 null）", example = "5")
    private Long lastSectionId;

    @Schema(description = "学习进度（0-100）", example = "50")
    private Integer progress;

    @Schema(description = "最后学习时间")
    private LocalDateTime lastStudyTime;

    @Schema(description = "完成时间")
    private LocalDateTime finishTime;

    @Schema(description = "状态（0-学习中 1-已完成）", example = "0")
    private Integer status;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
