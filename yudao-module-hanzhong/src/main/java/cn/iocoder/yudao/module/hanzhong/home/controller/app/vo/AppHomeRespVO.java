package cn.iocoder.yudao.module.hanzhong.home.controller.app.vo;

import cn.iocoder.yudao.module.hanzhong.banner.controller.app.vo.AppBannerRespVO;
import cn.iocoder.yudao.module.hanzhong.communitypost.controller.app.vo.AppCommunityPostRespVO;
import cn.iocoder.yudao.module.hanzhong.course.controller.app.vo.AppCourseRespVO;
import cn.iocoder.yudao.module.hanzhong.coursecategory.controller.app.vo.AppCourseCategoryRespVO;
import cn.iocoder.yudao.module.hanzhong.job.controller.app.vo.AppJobRespVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 用户 APP - 汉中首页 响应 VO
 *
 * @author hanzhong
 */
@Schema(description = "用户 APP - 汉中首页 Response VO")
@Data
public class AppHomeRespVO {

    @Schema(description = "首页 Banner 列表")
    private List<AppBannerRespVO> banners;

    @Schema(description = "课程分类列表")
    private List<AppCourseCategoryRespVO> courseCategories;

    @Schema(description = "推荐课程列表（前 8 条）")
    private List<AppCourseRespVO> featuredCourses;

    @Schema(description = "热门课程列表（按报名人数前 8 条）")
    private List<AppCourseRespVO> hotCourses;

    @Schema(description = "推荐职位列表（前 8 条）")
    private List<AppJobRespVO> featuredJobs;

    @Schema(description = "热门社区帖子列表（前 6 条，按浏览量降序）")
    private List<AppCommunityPostRespVO> hotPosts;

    @Schema(description = "最新社区帖子列表（前 6 条，按发布时间降序）")
    private List<AppCommunityPostRespVO> latestPosts;

}
