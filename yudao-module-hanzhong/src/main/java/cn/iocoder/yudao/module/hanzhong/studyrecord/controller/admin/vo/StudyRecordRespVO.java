package cn.iocoder.yudao.module.hanzhong.studyrecord.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 汉中 学习记录 响应 VO
 *
 * @author hanzhong
 */
@Schema(description = "管理后台 - 汉中 学习记录 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StudyRecordRespVO extends StudyRecordBaseVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    @Schema(description = "课程名称（快照）")
    private String courseName;

    @Schema(description = "课程封面（快照）")
    private String coverUrl;

    @Schema(description = "最后学习的章节编号", example = "5")
    private Long lastSectionId;

    @Schema(description = "学习进度（0-100）", example = "50")
    private Integer progress;

    @Schema(description = "最后学习时间")
    private LocalDateTime lastStudyTime;

    @Schema(description = "完成时间")
    private LocalDateTime finishTime;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
