package cn.iocoder.yudao.module.hanzhong.banner.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户 APP - 汉中 Banner 响应 VO
 *
 * @author hanzhong
 */
@Schema(description = "用户 APP - 汉中 Banner Response VO")
@Data
public class AppBannerRespVO {

    @Schema(description = "Banner 编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    @Schema(description = "标题", requiredMode = Schema.RequiredMode.REQUIRED, example = "首页活动 Banner")
    private String title;

    @Schema(description = "图片地址", requiredMode = Schema.RequiredMode.REQUIRED)
    private String picUrl;

    @Schema(description = "跳转类型（0-无跳转 1-内部页面 2-外部链接）", example = "0")
    private Integer linkType;

    @Schema(description = "跳转链接", example = "https://example.com")
    private String linkUrl;

    @Schema(description = "跳转业务 id", example = "1024")
    private Long linkId;

    @Schema(description = "排序", example = "1")
    private Integer sort;

    @Schema(description = "生效开始时间")
    private LocalDateTime startTime;

    @Schema(description = "生效结束时间")
    private LocalDateTime endTime;

}
