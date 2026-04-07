package cn.iocoder.yudao.module.hanzhong.communitypost.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 汉中 社区帖子置顶/精华更新 Request VO")
@Data
public class CommunityPostUpdateFeatureReqVO {

    @Schema(description = "帖子编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "帖子编号不能为空")
    private Long id;

    @Schema(description = "是否置顶", example = "true")
    private Boolean isTop;

    @Schema(description = "是否精华", example = "true")
    private Boolean isEssence;
}
