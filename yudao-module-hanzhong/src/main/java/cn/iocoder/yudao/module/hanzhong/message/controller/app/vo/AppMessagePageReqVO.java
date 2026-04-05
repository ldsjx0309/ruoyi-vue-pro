package cn.iocoder.yudao.module.hanzhong.message.controller.app.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 用户 APP - 汉中 消息 分页 Request VO
 *
 * @author hanzhong
 */
@Schema(description = "用户 APP - 汉中 消息 分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AppMessagePageReqVO extends PageParam {

    @Schema(description = "是否已读", example = "false")
    private Boolean isRead;

}
