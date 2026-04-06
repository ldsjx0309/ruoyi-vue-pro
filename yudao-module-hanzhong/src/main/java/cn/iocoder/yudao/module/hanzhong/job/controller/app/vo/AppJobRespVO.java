package cn.iocoder.yudao.module.hanzhong.job.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户 APP - 汉中 职位 响应 VO
 *
 * @author hanzhong
 */
@Schema(description = "用户 APP - 汉中 职位 Response VO")
@Data
public class AppJobRespVO {

    @Schema(description = "职位编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    @Schema(description = "职位名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "Java 开发工程师")
    private String title;

    @Schema(description = "公司名称", example = "汉中科技有限公司")
    private String company;

    @Schema(description = "薪资待遇", example = "10k-20k")
    private String salary;

    @Schema(description = "工作地点", example = "汉中市汉台区")
    private String location;

    @Schema(description = "职位类别", example = "技术研发")
    private String category;

    @Schema(description = "学历要求", example = "本科")
    private String education;

    @Schema(description = "经验要求", example = "3-5年")
    private String experience;

    @Schema(description = "招聘人数", example = "2")
    private Integer headcount;

    @Schema(description = "职位描述")
    private String description;

    @Schema(description = "联系人姓名", example = "张三")
    private String contactName;

    @Schema(description = "联系人电话", example = "13800138000")
    private String contactPhone;

    @Schema(description = "排序", example = "1")
    private Integer sort;

    @Schema(description = "当前登录用户是否已投递（匿名用户为 null）")
    private Boolean hasApplied;

    @Schema(description = "当前登录用户是否已收藏（匿名用户为 null）")
    private Boolean isCollected;

}
