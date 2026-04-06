package cn.iocoder.yudao.module.hanzhong.hotkeyword.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 管理后台 - 汉中 热门搜索关键词 响应 VO
 *
 * @author hanzhong
 */
@Schema(description = "管理后台 - 汉中 热门搜索关键词 Response VO")
@Data
public class HotKeywordRespVO {

    @Schema(description = "编号", example = "1")
    private Long id;

    @Schema(description = "关键词", example = "Java开发")
    private String keyword;

    @Schema(description = "排序", example = "1")
    private Integer sort;

    @Schema(description = "状态（0-禁用 1-启用）", example = "1")
    private Integer status;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
