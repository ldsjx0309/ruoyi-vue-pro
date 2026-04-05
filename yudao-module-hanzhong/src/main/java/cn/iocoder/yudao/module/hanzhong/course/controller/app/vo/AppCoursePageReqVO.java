package cn.iocoder.yudao.module.hanzhong.course.controller.app.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 用户 APP - 汉中 课程 分页 Request VO
 *
 * @author hanzhong
 */
@Schema(description = "用户 APP - 汉中 课程 分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AppCoursePageReqVO extends PageParam {

    @Schema(description = "课程标题", example = "Java 入门")
    private String title;

    @Schema(description = "分类编号", example = "1")
    private Long categoryId;

    @Schema(description = "排序方式（hot=按报名人数降序，new=按创建时间降序，默认按排序字段升序）", example = "hot")
    private String sortBy;

}
