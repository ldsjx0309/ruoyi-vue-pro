package cn.iocoder.yudao.module.hanzhong.coursecategory.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户 APP - 汉中 课程分类 响应 VO
 *
 * @author hanzhong
 */
@Schema(description = "用户 APP - 汉中 课程分类 Response VO")
@Data
public class AppCourseCategoryRespVO {

    @Schema(description = "课程分类编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    @Schema(description = "分类名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "职业技能")
    private String name;

    @Schema(description = "父分类编号", example = "0")
    private Long parentId;

    @Schema(description = "排序", example = "1")
    private Integer sort;

    @Schema(description = "图标", example = "icon-category")
    private String icon;

}
