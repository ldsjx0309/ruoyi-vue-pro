package cn.iocoder.yudao.module.hanzhong.coursecategory.controller.admin.vo;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.validation.InEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 汉中 课程分类 状态更新 Request VO
 *
 * @author hanzhong
 */
@Schema(description = "管理后台 - 汉中 课程分类 状态更新 Request VO")
@Data
public class CourseCategoryUpdateStatusReqVO {

    @Schema(description = "课程分类编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "课程分类编号不能为空")
    private Long id;

    @Schema(description = "状态（0-开启 1-关闭）", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    @NotNull(message = "状态不能为空")
    @InEnum(CommonStatusEnum.class)
    private Integer status;

}
