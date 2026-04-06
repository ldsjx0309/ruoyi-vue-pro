package cn.iocoder.yudao.module.hanzhong.studyrecord.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 用户 APP - 汉中 学习记录 更新进度 Request VO
 *
 * @author hanzhong
 */
@Schema(description = "用户 APP - 汉中 学习记录 更新进度 Request VO")
@Data
public class AppStudyRecordUpdateProgressReqVO {

    @Schema(description = "课程编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "课程编号不能为空")
    private Long courseId;

    @Schema(description = "当前学习的章节编号（可选，用于断点续播）", example = "5")
    private Long sectionId;

    @Schema(description = "学习进度（0-100）", requiredMode = Schema.RequiredMode.REQUIRED, example = "50")
    @NotNull(message = "学习进度不能为空")
    @Min(value = 0, message = "学习进度最小为 0")
    @Max(value = 100, message = "学习进度最大为 100")
    private Integer progress;

}
