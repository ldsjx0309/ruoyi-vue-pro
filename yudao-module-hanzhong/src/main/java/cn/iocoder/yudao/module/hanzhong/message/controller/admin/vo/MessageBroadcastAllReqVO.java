package cn.iocoder.yudao.module.hanzhong.message.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 管理后台 - 汉中 全员广播消息 Request VO
 *
 * @author hanzhong
 */
@Schema(description = "管理后台 - 汉中 全员广播消息 Request VO")
@Data
public class MessageBroadcastAllReqVO {

    @Schema(description = "消息类型（0-系统消息 1-通知 2-活动）", example = "1")
    private Integer type;

    @Schema(description = "消息标题", requiredMode = Schema.RequiredMode.REQUIRED, example = "系统公告")
    @NotBlank(message = "消息标题不能为空")
    private String title;

    @Schema(description = "消息内容", requiredMode = Schema.RequiredMode.REQUIRED, example = "尊敬的用户，...")
    @NotBlank(message = "消息内容不能为空")
    private String content;

}
