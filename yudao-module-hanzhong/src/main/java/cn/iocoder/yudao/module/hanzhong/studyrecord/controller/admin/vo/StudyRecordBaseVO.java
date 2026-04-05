package cn.iocoder.yudao.module.hanzhong.studyrecord.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 汉中 学习记录 Base VO
 *
 * @author hanzhong
 */
@Data
public class StudyRecordBaseVO {

    @Schema(description = "用户编号", example = "1024")
    private Long userId;

    @Schema(description = "课程编号", example = "1024")
    private Long courseId;

    @Schema(description = "状态（0-学习中 1-已完成）", example = "0")
    private Integer status;

    @Schema(description = "备注")
    private String remark;

}
