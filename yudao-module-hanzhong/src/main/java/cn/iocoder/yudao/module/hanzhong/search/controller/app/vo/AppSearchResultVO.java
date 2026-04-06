package cn.iocoder.yudao.module.hanzhong.search.controller.app.vo;

import cn.iocoder.yudao.module.hanzhong.communitypost.controller.app.vo.AppCommunityPostRespVO;
import cn.iocoder.yudao.module.hanzhong.course.controller.app.vo.AppCourseRespVO;
import cn.iocoder.yudao.module.hanzhong.job.controller.app.vo.AppJobRespVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 用户 APP - 汉中 统一搜索 响应 VO
 *
 * @author hanzhong
 */
@Schema(description = "用户 APP - 汉中 统一搜索 Response VO")
@Data
public class AppSearchResultVO {

    @Schema(description = "搜索关键词", example = "Java")
    private String keyword;

    @Schema(description = "课程搜索结果")
    private List<AppCourseRespVO> courses;

    @Schema(description = "职位搜索结果")
    private List<AppJobRespVO> jobs;

    @Schema(description = "社区帖子搜索结果")
    private List<AppCommunityPostRespVO> posts;

    @Schema(description = "课程总数", example = "10")
    private Long courseTotal;

    @Schema(description = "职位总数", example = "5")
    private Long jobTotal;

    @Schema(description = "帖子总数", example = "8")
    private Long postTotal;

    @Schema(description = "当前页码（type 非 all 时有意义）", example = "1")
    private Integer pageNo;

    @Schema(description = "每页条数（type 非 all 时有意义）", example = "10")
    private Integer pageSize;

}
