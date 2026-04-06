package cn.iocoder.yudao.module.hanzhong.coursesection.controller.app;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.hanzhong.course.dal.dataobject.CourseDO;
import cn.iocoder.yudao.module.hanzhong.course.service.CourseService;
import cn.iocoder.yudao.module.hanzhong.courseorder.dal.mysql.CourseOrderMapper;
import cn.iocoder.yudao.module.hanzhong.coursesection.controller.app.vo.AppCourseSectionRespVO;
import cn.iocoder.yudao.module.hanzhong.coursesection.convert.CourseSectionConvert;
import cn.iocoder.yudao.module.hanzhong.coursesection.dal.dataobject.CourseSectionDO;
import cn.iocoder.yudao.module.hanzhong.coursesection.service.CourseSectionService;
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
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 用户 APP - 汉中 课程章节
 *
 * @author hanzhong
 */
@RestController
@RequestMapping("/hanzhong/app/course-section")
@Tag(name = "用户 APP - 汉中 课程章节")
@Validated
public class AppCourseSectionController {

    @Resource
    private CourseSectionService courseSectionService;

    @Resource
    private CourseService courseService;

    @Resource
    private CourseOrderMapper courseOrderMapper;

    @GetMapping("/list")
    @Operation(summary = "根据课程编号获取章节列表")
    @Parameter(name = "courseId", description = "课程编号", required = true, example = "1024")
    @PermitAll
    public CommonResult<List<AppCourseSectionRespVO>> getSectionsByCourse(@RequestParam("courseId") Long courseId) {
        List<CourseSectionDO> sections = courseSectionService.getSectionsByCourseId(courseId);
        List<AppCourseSectionRespVO> sectionVOs = CourseSectionConvert.INSTANCE.convertAppList(sections);
        // 付费课程且用户未购买时，隐藏非免费试看章节的视频地址
        CourseDO course = courseService.getCourse(courseId);
        boolean isPaidCourse = course != null && course.getPrice() != null && course.getPrice() > 0;
        if (isPaidCourse) {
            Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
            boolean hasPurchased = loginUserId != null
                    && courseOrderMapper.selectActiveByUserIdAndCourseId(loginUserId, courseId) != null;
            if (!hasPurchased) {
                sectionVOs.forEach(s -> {
                    if (!Integer.valueOf(1).equals(s.getFreePreview())) {
                        s.setVideoUrl(null);
                    }
                });
            }
        }
        return success(sectionVOs);
    }

    @GetMapping("/get")
    @Operation(summary = "获取章节详情（付费课程非试看章节需已购买）")
    @Parameter(name = "id", description = "章节编号", required = true, example = "1024")
    @PermitAll
    public CommonResult<AppCourseSectionRespVO> getSection(@RequestParam("id") Long id) {
        CourseSectionDO section = courseSectionService.getSection(id);
        AppCourseSectionRespVO respVO = CourseSectionConvert.INSTANCE.convertApp(section);
        if (respVO == null) {
            return success(null);
        }
        // 非免费试看章节：需检查用户是否已购买所属课程
        if (!Integer.valueOf(1).equals(respVO.getFreePreview())) {
            CourseDO course = courseService.getCourse(respVO.getCourseId());
            boolean isPaidCourse = course != null && course.getPrice() != null && course.getPrice() > 0;
            if (isPaidCourse) {
                Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
                boolean hasPurchased = loginUserId != null
                        && courseOrderMapper.selectActiveByUserIdAndCourseId(loginUserId, respVO.getCourseId()) != null;
                if (!hasPurchased) {
                    respVO.setVideoUrl(null);
                }
            }
        }
        return success(respVO);
    }

}
