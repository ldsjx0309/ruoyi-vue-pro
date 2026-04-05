package cn.iocoder.yudao.module.hanzhong.communitypost.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 用户 APP - 汉中 社区帖子 更新 Request VO
 *
 * @author hanzhong
 */
@Schema(description = "用户 APP - 汉中 社区帖子 更新 Request VO")
@Data
public class AppCommunityPostUpdateReqVO {

    @Schema(description = "帖子编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "帖子编号不能为空")
    private Long id;

    @Schema(description = "标题", requiredMode = Schema.RequiredMode.REQUIRED, example = "更新后的标题")
    @NotBlank(message = "标题不能为空")
    private String title;

    @Schema(description = "内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "内容不能为空")
    private String content;

    @Schema(description = "封面图片地址")
    private String coverUrl;

    @Schema(description = "分类", example = "招聘")
    private String category;

}
