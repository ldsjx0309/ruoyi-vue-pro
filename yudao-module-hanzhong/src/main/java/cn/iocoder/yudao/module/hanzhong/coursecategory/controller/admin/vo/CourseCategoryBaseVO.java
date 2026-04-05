package cn.iocoder.yudao.module.hanzhong.coursecategory.controller.admin.vo;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.validation.InEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 汉中 课程分类 Base VO，供创建、修改、详情子 VO 使用
 *
 * @author hanzhong
 */
@Data
public class CourseCategoryBaseVO {

    @Schema(description = "分类名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "职业技能")
    @NotNull(message = "分类名称不能为空")
    private String name;

    @Schema(description = "父分类编号", example = "0")
    private Long parentId;

    @Schema(description = "排序", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "排序不能为空")
    private Integer sort;

    @Schema(description = "状态（0-开启 1-关闭）", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    @NotNull(message = "状态不能为空")
    @InEnum(CommonStatusEnum.class)
    private Integer status;

    @Schema(description = "图标", example = "icon-category")
    private String icon;

    @Schema(description = "备注")
    private String remark;

}
