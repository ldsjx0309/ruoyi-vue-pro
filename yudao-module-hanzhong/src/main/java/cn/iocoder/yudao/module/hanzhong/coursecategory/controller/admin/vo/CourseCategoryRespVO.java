package cn.iocoder.yudao.module.hanzhong.coursecategory.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 汉中 课程分类 响应 VO
 *
 * @author hanzhong
 */
@Schema(description = "管理后台 - 汉中 课程分类 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CourseCategoryRespVO extends CourseCategoryBaseVO {

    @Schema(description = "课程分类编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
