package cn.iocoder.yudao.module.hanzhong.courserating.controller.admin.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 管理后台 - 汉中 课程评分 分页请求 VO
 *
 * @author hanzhong
 */
@Schema(description = "管理后台 - 汉中 课程评分 分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class CourseRatingPageReqVO extends PageParam {

    @Schema(description = "课程编号", example = "1024")
    private Long courseId;

    @Schema(description = "用户编号", example = "100")
    private Long userId;

    @Schema(description = "评分（1-5）", example = "5")
    private Integer rating;

}
