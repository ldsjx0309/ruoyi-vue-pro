package cn.iocoder.yudao.module.hanzhong.hotkeyword.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 管理后台 - 汉中 热门搜索关键词 创建/更新 请求 VO
 *
 * @author hanzhong
 */
@Schema(description = "管理后台 - 汉中 热门搜索关键词 创建/更新 Request VO")
@Data
public class HotKeywordSaveReqVO {

    @Schema(description = "编号（更新时必填）", example = "1")
    private Long id;

    @Schema(description = "关键词", requiredMode = Schema.RequiredMode.REQUIRED, example = "Java开发")
    @NotBlank(message = "关键词不能为空")
    private String keyword;

    @Schema(description = "排序（升序）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "排序不能为空")
    private Integer sort;

    @Schema(description = "状态（0-禁用 1-启用）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "状态不能为空")
    private Integer status;

}
