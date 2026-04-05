package cn.iocoder.yudao.module.hanzhong.job.controller.admin.vo;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.validation.InEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 汉中 职位 Base VO，供创建、修改、详情子 VO 使用
 *
 * @author hanzhong
 */
@Data
public class JobBaseVO {

    @Schema(description = "职位名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "Java 开发工程师")
    @NotNull(message = "职位名称不能为空")
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

    @Schema(description = "排序", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "排序不能为空")
    private Integer sort;

    @Schema(description = "状态（0-开启 1-关闭）", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    @NotNull(message = "状态不能为空")
    @InEnum(CommonStatusEnum.class)
    private Integer status;

    @Schema(description = "备注")
    private String remark;

}
