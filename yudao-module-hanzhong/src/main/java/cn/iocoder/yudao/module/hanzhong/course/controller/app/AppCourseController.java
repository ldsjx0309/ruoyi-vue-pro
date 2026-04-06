package cn.iocoder.yudao.module.hanzhong.course.controller.app;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.hanzhong.course.controller.app.vo.AppCourseDetailVO;
import cn.iocoder.yudao.module.hanzhong.course.controller.app.vo.AppCoursePageReqVO;
import cn.iocoder.yudao.module.hanzhong.course.controller.app.vo.AppCourseRespVO;
import cn.iocoder.yudao.module.hanzhong.course.convert.CourseConvert;
import cn.iocoder.yudao.module.hanzhong.course.dal.dataobject.CourseDO;
import cn.iocoder.yudao.module.hanzhong.course.service.CourseService;
import cn.iocoder.yudao.module.hanzhong.courseorder.dal.mysql.CourseOrderMapper;
import cn.iocoder.yudao.module.hanzhong.coursefavorite.service.CourseFavoriteService;
import cn.iocoder.yudao.module.hanzhong.coursesection.controller.app.vo.AppCourseSectionRespVO;
import cn.iocoder.yudao.module.hanzhong.coursesection.convert.CourseSectionConvert;
import cn.iocoder.yudao.module.hanzhong.coursesection.dal.dataobject.CourseSectionDO;
import cn.iocoder.yudao.module.hanzhong.coursesection.service.CourseSectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 用户 APP - 汉中 课程
 *
 * @author hanzhong
 */
@RestController
@RequestMapping("/hanzhong/app/course")
@Tag(name = "用户 APP - 汉中 课程")
@Validated
public class AppCourseController {

    @Resource
    private CourseService courseService;

    @Resource
    private CourseOrderMapper courseOrderMapper;

    @Resource
    private CourseFavoriteService courseFavoriteService;

    @Resource
    private CourseSectionService courseSectionService;

    @GetMapping("/page")
    @Operation(summary = "获取课程分页列表")
    @PermitAll
    public CommonResult<PageResult<AppCourseRespVO>> getCoursePageForApp(@Valid AppCoursePageReqVO pageReqVO) {
        PageResult<CourseDO> pageResult = courseService.getCoursePageForApp(pageReqVO);
        return success(CourseConvert.INSTANCE.convertAppPage(pageResult));
    }

    @GetMapping("/get")
    @Operation(summary = "获取课程详情（不含章节列表，章节请用 /get-detail）")
    @Parameter(name = "id", description = "课程编号", required = true, example = "1024")
    @PermitAll
    public CommonResult<AppCourseRespVO> getCourse(@RequestParam("id") Long id) {
        CourseDO course = courseService.getCourse(id);
        if (course == null) {
            return success(null);
        }
        // 增加浏览量
        try {
            courseService.incrementViewCount(id);
        } catch (Exception ignored) {
            // 浏览量统计失败不影响主流程
        }
        AppCourseRespVO respVO = CourseConvert.INSTANCE.convertApp(course);
        // 如果用户已登录，设置是否已购买和是否已收藏标志
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        if (loginUserId != null) {
            respVO.setHasPurchased(courseOrderMapper.selectActiveByUserIdAndCourseId(loginUserId, id) != null);
            try {
                respVO.setIsFavorited(courseFavoriteService.isFavorited(loginUserId, id));
            } catch (Exception ignored) {
                // 收藏状态查询失败不影响主流程
            }
        }
        return success(respVO);
    }

    @GetMapping("/get-detail")
    @Operation(summary = "获取课程详情（含章节列表、总时长）")
    @Parameter(name = "id", description = "课程编号", required = true, example = "1024")
    @PermitAll
    public CommonResult<AppCourseDetailVO> getCourseDetail(@RequestParam("id") Long id) {
        CourseDO course = courseService.getCourse(id);
        if (course == null) {
            return success(null);
        }
        // 增加浏览量
        try {
            courseService.incrementViewCount(id);
        } catch (Exception ignored) {
            // 浏览量统计失败不影响主流程
        }
        // 构建详情 VO
        AppCourseDetailVO detailVO = new AppCourseDetailVO();
        // 复制基础字段
        AppCourseRespVO base = CourseConvert.INSTANCE.convertApp(course);
        detailVO.setId(base.getId());
        detailVO.setCategoryId(base.getCategoryId());
        detailVO.setTitle(base.getTitle());
        detailVO.setCoverUrl(base.getCoverUrl());
        detailVO.setSummary(base.getSummary());
        detailVO.setContent(base.getContent());
        detailVO.setTeacherName(base.getTeacherName());
        detailVO.setPrice(base.getPrice());
        detailVO.setOriginalPrice(base.getOriginalPrice());
        detailVO.setViewCount(base.getViewCount());
        detailVO.setEnrollCount(base.getEnrollCount());
        detailVO.setSort(base.getSort());

        // 如果用户已登录，设置是否已购买和是否已收藏标志
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        if (loginUserId != null) {
            detailVO.setHasPurchased(courseOrderMapper.selectActiveByUserIdAndCourseId(loginUserId, id) != null);
            try {
                detailVO.setIsFavorited(courseFavoriteService.isFavorited(loginUserId, id));
            } catch (Exception ignored) {
                // 收藏状态查询失败不影响主流程
            }
        }

        // 获取章节列表（仅显示已启用的章节，按排序升序）
        List<CourseSectionDO> sections = courseSectionService.getSectionsByCourseId(id);
        List<AppCourseSectionRespVO> sectionVOs = CourseSectionConvert.INSTANCE.convertAppList(sections);
        // 付费课程且用户未购买时，隐藏非免费试看章节的视频地址，防止未购买用户直接获取视频链接
        boolean isPaidCourse = course.getPrice() != null && course.getPrice() > 0;
        boolean hasPurchased = Boolean.TRUE.equals(detailVO.getHasPurchased());
        if (isPaidCourse && !hasPurchased) {
            sectionVOs.forEach(s -> {
                if (!Integer.valueOf(1).equals(s.getFreePreview())) {
                    s.setVideoUrl(null);
                }
            });
        }
        detailVO.setSections(sectionVOs);
        detailVO.setSectionCount(sectionVOs.size());
        // 计算总时长
        int totalDuration = sections.stream()
                .mapToInt(s -> s.getDuration() != null ? s.getDuration() : 0)
                .sum();
        detailVO.setTotalDuration(totalDuration);

        return success(detailVO);
    }

    @GetMapping("/has-purchased")
    @Operation(summary = "查询是否已购买课程")
    @Parameter(name = "courseId", description = "课程编号", required = true, example = "1024")
    @io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "Authorization")
    @org.springframework.security.access.prepost.PreAuthorize("isAuthenticated()")
    public CommonResult<Boolean> hasPurchased(@RequestParam("courseId") Long courseId) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        return success(courseOrderMapper.selectActiveByUserIdAndCourseId(userId, courseId) != null);
    }

}

