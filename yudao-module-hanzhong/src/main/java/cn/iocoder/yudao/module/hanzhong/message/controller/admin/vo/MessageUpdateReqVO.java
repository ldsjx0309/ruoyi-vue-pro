package cn.iocoder.yudao.module.hanzhong.message.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * 汉中 消息 更新 Request VO
 *
 * @author hanzhong
 */
@Schema(description = "管理后台 - 汉中 消息 更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MessageUpdateReqVO extends MessageBaseVO {

    @Schema(description = "消息编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "消息编号不能为空")
    private Long id;

}
