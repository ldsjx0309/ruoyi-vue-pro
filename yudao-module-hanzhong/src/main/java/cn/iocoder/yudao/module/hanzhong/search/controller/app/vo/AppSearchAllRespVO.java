package cn.iocoder.yudao.module.hanzhong.search.controller.app.vo;

import cn.iocoder.yudao.module.hanzhong.communitypost.controller.app.vo.AppCommunityPostRespVO;
import cn.iocoder.yudao.module.hanzhong.course.controller.app.vo.AppCourseRespVO;
import cn.iocoder.yudao.module.hanzhong.job.controller.app.vo.AppJobRespVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 用户 APP - 汉中 综合搜索（/all）响应 VO
 * 每类最多返回 5 条，适合首页快速预览
 *
 * @author hanzhong
 */
@Schema(description = "用户 APP - 汉中 综合搜索 /all Response VO")
@Data
public class AppSearchAllRespVO {

    @Schema(description = "搜索关键词", example = "Java")
    private String keyword;

    @Schema(description = "课程搜索结果（最多 5 条）")
    private List<AppCourseRespVO> courses;

    @Schema(description = "职位搜索结果（最多 5 条）")
    private List<AppJobRespVO> jobs;

    @Schema(description = "社区帖子搜索结果（最多 5 条）")
    private List<AppCommunityPostRespVO> posts;

}
