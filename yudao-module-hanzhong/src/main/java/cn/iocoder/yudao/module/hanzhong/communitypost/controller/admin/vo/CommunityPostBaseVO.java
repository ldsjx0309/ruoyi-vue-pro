package cn.iocoder.yudao.module.hanzhong.communitypost.controller.admin.vo;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.validation.InEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 汉中 社区帖子 Base VO
 *
 * @author hanzhong
 */
@Data
public class CommunityPostBaseVO {

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

    @Schema(description = "是否置顶")
    private Boolean isTop;

    @Schema(description = "是否精华")
    private Boolean isEssence;

    @Schema(description = "状态（0-正常 1-已下线）", example = "0")
    private Integer status;

    @Schema(description = "备注")
    private String remark;

}
