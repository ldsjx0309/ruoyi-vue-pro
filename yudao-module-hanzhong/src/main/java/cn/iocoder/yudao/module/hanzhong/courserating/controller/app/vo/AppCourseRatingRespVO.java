package cn.iocoder.yudao.module.hanzhong.courserating.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户 APP - 汉中 课程评分 响应 VO
 *
 * @author hanzhong
 */
@Schema(description = "用户 APP - 汉中 课程评分 Response VO")
@Data
public class AppCourseRatingRespVO {

    @Schema(description = "评分记录编号", example = "1")
    private Long id;

    @Schema(description = "用户编号", example = "100")
    private Long userId;

    @Schema(description = "课程编号", example = "1024")
    private Long courseId;

    @Schema(description = "课程名称", example = "Java 零基础入门")
    private String courseName;

    @Schema(description = "评分（1-5 星）", example = "5")
    private Integer rating;

    @Schema(description = "评价内容", example = "课程内容很充实！")
    private String comment;

    @Schema(description = "评价时间")
    private LocalDateTime createTime;

}
