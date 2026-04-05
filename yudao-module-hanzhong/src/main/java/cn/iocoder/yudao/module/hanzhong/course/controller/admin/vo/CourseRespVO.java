package cn.iocoder.yudao.module.hanzhong.course.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 汉中 课程 响应 VO
 *
 * @author hanzhong
 */
@Schema(description = "管理后台 - 汉中 课程 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CourseRespVO extends CourseBaseVO {

    @Schema(description = "课程编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    @Schema(description = "浏览量", example = "100")
    private Integer viewCount;

    @Schema(description = "报名人数", example = "50")
    private Integer enrollCount;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
