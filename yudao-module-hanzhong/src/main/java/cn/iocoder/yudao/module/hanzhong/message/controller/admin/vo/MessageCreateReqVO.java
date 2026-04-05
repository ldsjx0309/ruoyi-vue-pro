package cn.iocoder.yudao.module.hanzhong.message.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 汉中 消息 创建 Request VO
 *
 * @author hanzhong
 */
@Schema(description = "管理后台 - 汉中 消息 创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MessageCreateReqVO extends MessageBaseVO {

}
