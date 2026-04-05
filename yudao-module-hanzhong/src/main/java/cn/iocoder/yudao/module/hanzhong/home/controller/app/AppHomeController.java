package cn.iocoder.yudao.module.hanzhong.home.controller.app;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.hanzhong.banner.controller.app.vo.AppBannerRespVO;
import cn.iocoder.yudao.module.hanzhong.banner.convert.BannerConvert;
import cn.iocoder.yudao.module.hanzhong.banner.service.BannerService;
import cn.iocoder.yudao.module.hanzhong.course.controller.app.vo.AppCoursePageReqVO;
import cn.iocoder.yudao.module.hanzhong.course.controller.app.vo.AppCourseRespVO;
import cn.iocoder.yudao.module.hanzhong.course.convert.CourseConvert;
import cn.iocoder.yudao.module.hanzhong.course.service.CourseService;
import cn.iocoder.yudao.module.hanzhong.coursecategory.controller.app.vo.AppCourseCategoryRespVO;
import cn.iocoder.yudao.module.hanzhong.coursecategory.convert.CourseCategoryConvert;
import cn.iocoder.yudao.module.hanzhong.coursecategory.service.CourseCategoryService;
import cn.iocoder.yudao.module.hanzhong.home.controller.app.vo.AppHomeRespVO;
import cn.iocoder.yudao.module.hanzhong.job.controller.app.vo.AppJobPageReqVO;
import cn.iocoder.yudao.module.hanzhong.job.controller.app.vo.AppJobRespVO;
import cn.iocoder.yudao.module.hanzhong.job.convert.JobConvert;
import cn.iocoder.yudao.module.hanzhong.job.service.JobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 用户 APP - 汉中首页聚合接口
 *
 * @author hanzhong
 */
@RestController
@RequestMapping("/hanzhong/app/home")
@Tag(name = "用户 APP - 汉中首页")
@Validated
public class AppHomeController {

    private static final int FEATURED_PAGE_SIZE = 8;

    @Resource
    private BannerService bannerService;

    @Resource
    private CourseService courseService;

    @Resource
    private CourseCategoryService courseCategoryService;

    @Resource
    private JobService jobService;

    @GetMapping
    @Operation(summary = "获取首页聚合数据（Banner + 推荐课程 + 推荐职位）")
    @PermitAll
    public CommonResult<AppHomeRespVO> getHomeData() {
        AppHomeRespVO respVO = new AppHomeRespVO();

        // 有效 Banner 列表
        List<AppBannerRespVO> banners = BannerConvert.INSTANCE.convertAppList(bannerService.getActiveBannerList());
        respVO.setBanners(banners);

        // 课程分类列表
        List<AppCourseCategoryRespVO> courseCategories = CourseCategoryConvert.INSTANCE.convertAppList(
                courseCategoryService.getEnabledCourseCategoryList());
        respVO.setCourseCategories(courseCategories);

        // 推荐课程（前 8 条，按排序升序）
        AppCoursePageReqVO coursePageReq = new AppCoursePageReqVO();
        coursePageReq.setPageNo(1);
        coursePageReq.setPageSize(FEATURED_PAGE_SIZE);
        List<AppCourseRespVO> featuredCourses = CourseConvert.INSTANCE.convertAppList(
                courseService.getCoursePageForApp(coursePageReq).getList());
        respVO.setFeaturedCourses(featuredCourses);

        // 热门课程（前 8 条，按报名人数降序）
        AppCoursePageReqVO hotCoursePageReq = new AppCoursePageReqVO();
        hotCoursePageReq.setPageNo(1);
        hotCoursePageReq.setPageSize(FEATURED_PAGE_SIZE);
        hotCoursePageReq.setSortBy("hot");
        List<AppCourseRespVO> hotCourses = CourseConvert.INSTANCE.convertAppList(
                courseService.getCoursePageForApp(hotCoursePageReq).getList());
        respVO.setHotCourses(hotCourses);

        // 推荐职位（前 8 条，按排序升序）
        AppJobPageReqVO jobPageReq = new AppJobPageReqVO();
        jobPageReq.setPageNo(1);
        jobPageReq.setPageSize(FEATURED_PAGE_SIZE);
        List<AppJobRespVO> featuredJobs = JobConvert.INSTANCE.convertAppPage(
                jobService.getJobPageForApp(jobPageReq)).getList();
        respVO.setFeaturedJobs(featuredJobs);

        return success(respVO);
    }

}
