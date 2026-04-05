package cn.iocoder.yudao.module.hanzhong.communitypost.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户 APP - 汉中 社区帖子 响应 VO
 *
 * @author hanzhong
 */
@Schema(description = "用户 APP - 汉中 社区帖子 Response VO")
@Data
public class AppCommunityPostRespVO {

    @Schema(description = "编号", example = "1024")
    private Long id;

    @Schema(description = "发布者用户编号", example = "1024")
    private Long userId;

    @Schema(description = "标题", example = "求职招聘帖子")
    private String title;

    @Schema(description = "内容")
    private String content;

    @Schema(description = "封面图片地址")
    private String coverUrl;

    @Schema(description = "分类", example = "招聘")
    private String category;

    @Schema(description = "浏览量", example = "100")
    private Integer viewCount;

    @Schema(description = "点赞数", example = "50")
    private Integer likeCount;

    @Schema(description = "评论数", example = "10")
    private Integer commentCount;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
