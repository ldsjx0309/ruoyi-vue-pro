package cn.iocoder.yudao.module.hanzhong.job.controller.app.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 用户 APP - 汉中 职位 分页 Request VO
 *
 * @author hanzhong
 */
@Schema(description = "用户 APP - 汉中 职位 分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AppJobPageReqVO extends PageParam {

    @Schema(description = "职位名称", example = "Java 工程师")
    private String title;

    @Schema(description = "职位类别", example = "技术研发")
    private String category;

    @Schema(description = "行业", example = "互联网")
    private String industry;

    @Schema(description = "职位类型", example = "全职")
    private String jobType;

    @Schema(description = "企业类型", example = "大企业")
    private String enterpriseType;

    @Schema(description = "工作地点（模糊匹配）", example = "汉中")
    private String location;

    @Schema(description = "是否只看 AI 推荐", example = "false")
    private Boolean aiRecommended;

}
