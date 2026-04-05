package cn.iocoder.yudao.module.hanzhong.coursesection.controller.app;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
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

    @GetMapping("/list")
    @Operation(summary = "根据课程编号获取章节列表")
    @Parameter(name = "courseId", description = "课程编号", required = true, example = "1024")
    @PermitAll
    public CommonResult<List<AppCourseSectionRespVO>> getSectionsByCourse(@RequestParam("courseId") Long courseId) {
        List<CourseSectionDO> sections = courseSectionService.getSectionsByCourseId(courseId);
        return success(CourseSectionConvert.INSTANCE.convertAppList(sections));
    }

    @GetMapping("/get")
    @Operation(summary = "获取章节详情")
    @Parameter(name = "id", description = "章节编号", required = true, example = "1024")
    @PermitAll
    public CommonResult<AppCourseSectionRespVO> getSection(@RequestParam("id") Long id) {
        CourseSectionDO section = courseSectionService.getSection(id);
        return success(CourseSectionConvert.INSTANCE.convertApp(section));
    }

}
