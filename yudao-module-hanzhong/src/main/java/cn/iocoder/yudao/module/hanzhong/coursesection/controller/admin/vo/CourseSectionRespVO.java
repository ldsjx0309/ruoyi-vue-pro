package cn.iocoder.yudao.module.hanzhong.coursesection.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 课程章节 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CourseSectionRespVO extends CourseSectionBaseVO {

    @Schema(description = "编号", example = "1024")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
