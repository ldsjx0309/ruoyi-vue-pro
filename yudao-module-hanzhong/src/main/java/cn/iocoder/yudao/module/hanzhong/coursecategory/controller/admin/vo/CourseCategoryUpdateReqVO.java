package cn.iocoder.yudao.module.hanzhong.coursecategory.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * 汉中 课程分类 更新 Request VO
 *
 * @author hanzhong
 */
@Schema(description = "管理后台 - 汉中 课程分类 更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CourseCategoryUpdateReqVO extends CourseCategoryBaseVO {

    @Schema(description = "课程分类编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "课程分类编号不能为空")
    private Long id;

}
