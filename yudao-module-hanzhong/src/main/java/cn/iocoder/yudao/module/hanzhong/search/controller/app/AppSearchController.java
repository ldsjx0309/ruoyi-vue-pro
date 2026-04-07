package cn.iocoder.yudao.module.hanzhong.search.controller.app;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.communitypost.controller.app.vo.AppCommunityPostPageReqVO;
import cn.iocoder.yudao.module.hanzhong.communitypost.controller.app.vo.AppCommunityPostRespVO;
import cn.iocoder.yudao.module.hanzhong.communitypost.convert.CommunityPostConvert;
import cn.iocoder.yudao.module.hanzhong.communitypost.dal.dataobject.CommunityPostDO;
import cn.iocoder.yudao.module.hanzhong.communitypost.service.CommunityPostService;
import cn.iocoder.yudao.module.hanzhong.course.controller.app.vo.AppCoursePageReqVO;
import cn.iocoder.yudao.module.hanzhong.course.controller.app.vo.AppCourseRespVO;
import cn.iocoder.yudao.module.hanzhong.course.convert.CourseConvert;
import cn.iocoder.yudao.module.hanzhong.course.dal.dataobject.CourseDO;
import cn.iocoder.yudao.module.hanzhong.course.service.CourseService;
import cn.iocoder.yudao.module.hanzhong.job.controller.app.vo.AppJobPageReqVO;
import cn.iocoder.yudao.module.hanzhong.job.controller.app.vo.AppJobRespVO;
import cn.iocoder.yudao.module.hanzhong.job.convert.JobConvert;
import cn.iocoder.yudao.module.hanzhong.job.dal.dataobject.JobDO;
import cn.iocoder.yudao.module.hanzhong.job.service.JobService;
import cn.iocoder.yudao.module.hanzhong.hotkeyword.service.HotKeywordService;
import cn.iocoder.yudao.module.hanzhong.search.controller.app.vo.AppSearchAllRespVO;
import cn.iocoder.yudao.module.hanzhong.search.controller.app.vo.AppSearchReqVO;
import cn.iocoder.yudao.module.hanzhong.search.controller.app.vo.AppSearchResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 用户 APP - 汉中 统一搜索
 *
 * @author hanzhong
 */
@RestController
@RequestMapping("/hanzhong/app/search")
@Tag(name = "用户 APP - 汉中 统一搜索")
@Validated
public class AppSearchController {

    @Resource
    private CourseService courseService;

    @Resource
    private JobService jobService;

    @Resource
    private CommunityPostService communityPostService;

    @Resource
    private HotKeywordService hotKeywordService;

    @GetMapping
    @Operation(summary = "统一搜索（课程 + 职位 + 社区帖子）")
    @PermitAll
    public CommonResult<AppSearchResultVO> search(@Valid AppSearchReqVO reqVO) {
        String keyword = reqVO.getKeyword().trim();
        String type = reqVO.getType() == null ? "all" : reqVO.getType();
        // 聚合搜索时 limit 限制每类数量，type-specific 搜索时使用分页参数
        int limit = (reqVO.getLimit() == null || reqVO.getLimit() <= 0 || reqVO.getLimit() > 20) ? 5 : reqVO.getLimit();
        int pageNo = (reqVO.getPageNo() == null || reqVO.getPageNo() < 1) ? 1 : reqVO.getPageNo();
        int pageSize = (reqVO.getPageSize() == null || reqVO.getPageSize() <= 0 || reqVO.getPageSize() > 50) ? 10 : reqVO.getPageSize();
        boolean isSpecificType = !"all".equals(type);

        AppSearchResultVO result = new AppSearchResultVO();
        result.setKeyword(keyword);
        if (isSpecificType) {
            result.setPageNo(pageNo);
            result.setPageSize(pageSize);
        }

        int effectivePageNo = isSpecificType ? pageNo : 1;
        int effectivePageSize = isSpecificType ? pageSize : limit;

        // 搜索课程
        if ("all".equals(type) || "course".equals(type)) {
            AppCoursePageReqVO courseReq = new AppCoursePageReqVO();
            courseReq.setTitle(keyword);
            courseReq.setPageNo(effectivePageNo);
            courseReq.setPageSize(effectivePageSize);
            PageResult<CourseDO> coursePage = courseService.getCoursePageForApp(courseReq);
            List<AppCourseRespVO> courses = CourseConvert.INSTANCE.convertAppList(coursePage.getList());
            result.setCourses(courses);
            result.setCourseTotal(coursePage.getTotal());
        } else {
            result.setCourses(Collections.emptyList());
            result.setCourseTotal(0L);
        }

        // 搜索职位
        if ("all".equals(type) || "job".equals(type)) {
            AppJobPageReqVO jobReq = new AppJobPageReqVO();
            jobReq.setTitle(keyword);
            jobReq.setPageNo(effectivePageNo);
            jobReq.setPageSize(effectivePageSize);
            PageResult<JobDO> jobPage = jobService.getJobPageForApp(jobReq);
            List<AppJobRespVO> jobs = JobConvert.INSTANCE.convertAppPage(jobPage).getList();
            result.setJobs(jobs);
            result.setJobTotal(jobPage.getTotal());
        } else {
            result.setJobs(Collections.emptyList());
            result.setJobTotal(0L);
        }

        // 搜索社区帖子
        if ("all".equals(type) || "post".equals(type)) {
            AppCommunityPostPageReqVO postReq = new AppCommunityPostPageReqVO();
            postReq.setKeyword(keyword);
            postReq.setPageNo(effectivePageNo);
            postReq.setPageSize(effectivePageSize);
            PageResult<CommunityPostDO> postPage = communityPostService.getPostPageForApp(postReq);
            List<AppCommunityPostRespVO> posts = CommunityPostConvert.INSTANCE.convertAppList(postPage.getList());
            result.setPosts(posts);
            result.setPostTotal(postPage.getTotal());
        } else {
            result.setPosts(Collections.emptyList());
            result.setPostTotal(0L);
        }

        return success(result);
    }

    @GetMapping("/hot-keywords")
    @Operation(summary = "获取热门搜索词（从数据库加载，管理员可配置；无数据时返回预置词组）")
    @PermitAll
    public CommonResult<List<String>> getHotKeywords() {
        return success(hotKeywordService.getEnabledKeywords());
    }

    @GetMapping("/all")
    @Operation(summary = "综合搜索（课程 + 职位 + 帖子各最多 5 条）")
    @Parameter(name = "keyword", description = "搜索关键词", required = true, example = "Java")
    @PermitAll
    public CommonResult<AppSearchAllRespVO> searchAll(@RequestParam("keyword") String keyword) {
        String kw = keyword == null ? "" : keyword.trim();
        AppSearchAllRespVO result = new AppSearchAllRespVO();
        result.setKeyword(kw);

        AppCoursePageReqVO courseReq = new AppCoursePageReqVO();
        courseReq.setTitle(kw);
        courseReq.setPageNo(1);
        courseReq.setPageSize(5);
        PageResult<CourseDO> coursePage = courseService.getCoursePageForApp(courseReq);
        result.setCourses(CourseConvert.INSTANCE.convertAppList(coursePage.getList()));

        AppJobPageReqVO jobReq = new AppJobPageReqVO();
        jobReq.setTitle(kw);
        jobReq.setPageNo(1);
        jobReq.setPageSize(5);
        PageResult<JobDO> jobPage = jobService.getJobPageForApp(jobReq);
        result.setJobs(JobConvert.INSTANCE.convertAppPage(jobPage).getList());

        AppCommunityPostPageReqVO postReq = new AppCommunityPostPageReqVO();
        postReq.setKeyword(kw);
        postReq.setPageNo(1);
        postReq.setPageSize(5);
        PageResult<CommunityPostDO> postPage = communityPostService.getPostPageForApp(postReq);
        result.setPosts(CommunityPostConvert.INSTANCE.convertAppList(postPage.getList()));

        return success(result);
    }

}
