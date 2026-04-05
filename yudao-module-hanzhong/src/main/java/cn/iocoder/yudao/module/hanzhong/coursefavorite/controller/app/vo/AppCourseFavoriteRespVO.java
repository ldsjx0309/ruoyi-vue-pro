package cn.iocoder.yudao.module.hanzhong.coursefavorite.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "用户 APP - 课程收藏 Response VO")
@Data
public class AppCourseFavoriteRespVO {

    @Schema(description = "收藏编号", example = "1024")
    private Long id;

    @Schema(description = "课程编号", example = "1")
    private Long courseId;

    @Schema(description = "课程名称", example = "Java 入门课程")
    private String courseName;

    @Schema(description = "课程封面", example = "https://example.com/cover.jpg")
    private String coverUrl;

    @Schema(description = "收藏时间")
    private LocalDateTime createTime;

}
