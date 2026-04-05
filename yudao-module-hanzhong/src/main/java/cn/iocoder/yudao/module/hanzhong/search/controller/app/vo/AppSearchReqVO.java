package cn.iocoder.yudao.module.hanzhong.search.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 用户 APP - 汉中 统一搜索 请求 VO
 *
 * @author hanzhong
 */
@Schema(description = "用户 APP - 汉中 统一搜索 Request VO")
@Data
public class AppSearchReqVO {

    @Schema(description = "搜索关键词", requiredMode = Schema.RequiredMode.REQUIRED, example = "Java")
    @NotBlank(message = "关键词不能为空")
    @Size(max = 50, message = "关键词最多 50 个字符")
    private String keyword;

    @Schema(description = "搜索类型：all-全部 course-课程 job-职位 post-社区帖子", example = "all")
    private String type = "all";

    @Schema(description = "每个类型返回的条数（默认 5）", example = "5")
    private Integer limit = 5;

}
