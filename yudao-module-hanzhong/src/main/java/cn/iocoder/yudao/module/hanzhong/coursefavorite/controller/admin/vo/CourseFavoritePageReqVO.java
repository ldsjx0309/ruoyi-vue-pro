package cn.iocoder.yudao.module.hanzhong.coursefavorite.controller.admin.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 管理后台 - 汉中 课程收藏分页请求 VO
 *
 * @author hanzhong
 */
@Schema(description = "管理后台 - 汉中 课程收藏分页请求 VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class CourseFavoritePageReqVO extends PageParam {

    @Schema(description = "用户编号", example = "1024")
    private Long userId;

    @Schema(description = "课程编号", example = "2048")
    private Long courseId;

    @Schema(description = "课程名称（模糊搜索）", example = "Java")
    private String courseName;

}
