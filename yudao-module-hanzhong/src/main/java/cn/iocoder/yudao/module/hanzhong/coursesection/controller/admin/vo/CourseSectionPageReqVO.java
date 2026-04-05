package cn.iocoder.yudao.module.hanzhong.coursesection.controller.admin.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 课程章节分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CourseSectionPageReqVO extends PageParam {

    @Schema(description = "所属课程编号", example = "1")
    private Long courseId;

    @Schema(description = "章节标题", example = "第一章")
    private String title;

    @Schema(description = "状态（0-开启 1-关闭）", example = "0")
    private Integer status;

}
