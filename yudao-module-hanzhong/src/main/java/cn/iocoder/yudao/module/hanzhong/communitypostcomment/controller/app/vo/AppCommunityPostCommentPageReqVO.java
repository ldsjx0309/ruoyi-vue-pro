package cn.iocoder.yudao.module.hanzhong.communitypostcomment.controller.app.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "用户 APP - 帖子评论分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AppCommunityPostCommentPageReqVO extends PageParam {

    @Schema(description = "帖子编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "帖子编号不能为空")
    private Long postId;

}
