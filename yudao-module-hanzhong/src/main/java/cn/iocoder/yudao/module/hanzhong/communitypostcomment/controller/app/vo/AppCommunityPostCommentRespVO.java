package cn.iocoder.yudao.module.hanzhong.communitypostcomment.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "用户 APP - 帖子评论 Response VO")
@Data
public class AppCommunityPostCommentRespVO {

    @Schema(description = "编号", example = "1024")
    private Long id;

    @Schema(description = "帖子编号", example = "1024")
    private Long postId;

    @Schema(description = "评论用户编号", example = "1")
    private Long userId;

    @Schema(description = "评论内容", example = "这篇文章写得很好！")
    private String content;

    @Schema(description = "父评论编号（0-顶层评论）", example = "0")
    private Long parentId;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
