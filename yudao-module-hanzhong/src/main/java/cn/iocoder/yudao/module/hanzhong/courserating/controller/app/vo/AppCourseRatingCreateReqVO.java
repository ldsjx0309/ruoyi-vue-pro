package cn.iocoder.yudao.module.hanzhong.courserating.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 用户 APP - 汉中 课程评分 创建/更新 请求 VO
 *
 * @author hanzhong
 */
@Schema(description = "用户 APP - 汉中 课程评分 创建/更新 Request VO")
@Data
public class AppCourseRatingCreateReqVO {

    @Schema(description = "课程编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "课程编号不能为空")
    private Long courseId;

    @Schema(description = "评分（1-5 星）", requiredMode = Schema.RequiredMode.REQUIRED, example = "5")
    @NotNull(message = "评分不能为空")
    @Min(value = 1, message = "评分最低为 1 星")
    @Max(value = 5, message = "评分最高为 5 星")
    private Integer rating;

    @Schema(description = "评价内容（可选，最多 500 字）", example = "课程内容很充实，讲师讲解清晰！")
    private String comment;

}
