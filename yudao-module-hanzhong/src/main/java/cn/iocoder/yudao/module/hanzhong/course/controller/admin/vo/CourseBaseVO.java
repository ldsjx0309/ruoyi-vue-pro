package cn.iocoder.yudao.module.hanzhong.course.controller.admin.vo;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.validation.InEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 汉中 课程 Base VO，供创建、修改、详情子 VO 使用
 *
 * @author hanzhong
 */
@Data
public class CourseBaseVO {

    @Schema(description = "分类编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "分类编号不能为空")
    private Long categoryId;

    @Schema(description = "课程标题", requiredMode = Schema.RequiredMode.REQUIRED, example = "Java 零基础入门")
    @NotNull(message = "课程标题不能为空")
    private String title;

    @Schema(description = "封面图片地址")
    private String coverUrl;

    @Schema(description = "简介")
    private String summary;

    @Schema(description = "课程内容")
    private String content;

    @Schema(description = "讲师名称")
    private String teacherName;

    @Schema(description = "价格（分）", example = "0")
    private Integer price;

    @Schema(description = "原价（分）", example = "9900")
    private Integer originalPrice;

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
