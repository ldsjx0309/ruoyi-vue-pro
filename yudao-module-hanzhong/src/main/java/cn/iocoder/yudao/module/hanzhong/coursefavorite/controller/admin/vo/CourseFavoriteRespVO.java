package cn.iocoder.yudao.module.hanzhong.coursefavorite.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 管理后台 - 汉中 课程收藏响应 VO
 *
 * @author hanzhong
 */
@Schema(description = "管理后台 - 汉中 课程收藏 Response VO")
@Data
public class CourseFavoriteRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long userId;

    @Schema(description = "课程编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "2048")
    private Long courseId;

    @Schema(description = "课程名称", example = "Java 零基础入门")
    private String courseName;

    @Schema(description = "课程封面图 URL", example = "https://example.com/cover.jpg")
    private String coverUrl;

    @Schema(description = "创建时间（收藏时间）", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
