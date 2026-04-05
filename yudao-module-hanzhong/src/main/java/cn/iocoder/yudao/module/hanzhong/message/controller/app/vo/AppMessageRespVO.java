package cn.iocoder.yudao.module.hanzhong.message.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户 APP - 汉中 消息 响应 VO
 *
 * @author hanzhong
 */
@Schema(description = "用户 APP - 汉中 消息 Response VO")
@Data
public class AppMessageRespVO {

    @Schema(description = "消息编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    @Schema(description = "标题", requiredMode = Schema.RequiredMode.REQUIRED, example = "系统通知")
    private String title;

    @Schema(description = "内容", requiredMode = Schema.RequiredMode.REQUIRED)
    private String content;

    @Schema(description = "消息类型（0-系统消息 1-通知 2-活动）", example = "0")
    private Integer type;

    @Schema(description = "是否已读", example = "false")
    private Boolean isRead;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
