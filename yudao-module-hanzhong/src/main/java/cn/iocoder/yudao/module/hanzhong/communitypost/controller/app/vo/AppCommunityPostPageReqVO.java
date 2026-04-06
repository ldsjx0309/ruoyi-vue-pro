package cn.iocoder.yudao.module.hanzhong.communitypost.controller.app.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 用户 APP - 汉中 社区帖子 分页 Request VO
 *
 * @author hanzhong
 */
@Schema(description = "用户 APP - 汉中 社区帖子 分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AppCommunityPostPageReqVO extends PageParam {

    @Schema(description = "分类", example = "招聘")
    private String category;

    @Schema(description = "关键词（匹配标题）", example = "求职")
    private String keyword;

    @Schema(description = "用户编号（按指定用户过滤，用于查看他人主页帖子）", example = "1024")
    private Long userId;

}
