package cn.iocoder.yudao.module.hanzhong.message.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 汉中 消息 Base VO，供创建、修改、详情子 VO 使用
 *
 * @author hanzhong
 */
@Data
public class MessageBaseVO {

    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "用户编号不能为空")
    private Long userId;

    @Schema(description = "标题", requiredMode = Schema.RequiredMode.REQUIRED, example = "系统通知")
    @NotNull(message = "标题不能为空")
    private String title;

    @Schema(description = "内容", requiredMode = Schema.RequiredMode.REQUIRED, example = "您的账号已完成实名认证")
    @NotNull(message = "内容不能为空")
    private String content;

    @Schema(description = "消息类型（0-系统消息 1-通知 2-活动）", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    @NotNull(message = "消息类型不能为空")
    private Integer type;

    @Schema(description = "备注")
    private String remark;

}
