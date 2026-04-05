package cn.iocoder.yudao.module.hanzhong.communitypost.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 汉中 社区帖子 状态更新 Request VO
 *
 * @author hanzhong
 */
@Schema(description = "管理后台 - 汉中 社区帖子 状态更新 Request VO")
@Data
public class CommunityPostUpdateStatusReqVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "编号不能为空")
    private Long id;

    @Schema(description = "状态（0-正常 1-已下线）", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    @NotNull(message = "状态不能为空")
    private Integer status;

}
