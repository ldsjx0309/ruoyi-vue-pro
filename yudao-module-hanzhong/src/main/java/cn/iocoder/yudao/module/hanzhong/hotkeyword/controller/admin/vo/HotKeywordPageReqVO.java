package cn.iocoder.yudao.module.hanzhong.hotkeyword.controller.admin.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 管理后台 - 汉中 热门搜索关键词 分页请求 VO
 *
 * @author hanzhong
 */
@Schema(description = "管理后台 - 汉中 热门搜索关键词 分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class HotKeywordPageReqVO extends PageParam {

    @Schema(description = "关键词（模糊搜索）", example = "Java")
    private String keyword;

    @Schema(description = "状态（0-禁用 1-启用）", example = "1")
    private Integer status;

}
