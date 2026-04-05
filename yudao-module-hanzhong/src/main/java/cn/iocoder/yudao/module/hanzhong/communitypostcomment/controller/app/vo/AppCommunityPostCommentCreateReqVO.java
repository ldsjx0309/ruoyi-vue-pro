package cn.iocoder.yudao.module.hanzhong.communitypostcomment.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Schema(description = "用户 APP - 发布帖子评论 Request VO")
@Data
public class AppCommunityPostCommentCreateReqVO {

    @Schema(description = "帖子编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "帖子编号不能为空")
    private Long postId;

    @Schema(description = "评论内容", requiredMode = Schema.RequiredMode.REQUIRED, example = "这篇文章写得很好！")
    @NotBlank(message = "评论内容不能为空")
    private String content;

    @Schema(description = "父评论编号（0 或不传表示顶层评论）", example = "0")
    private Long parentId;

}
