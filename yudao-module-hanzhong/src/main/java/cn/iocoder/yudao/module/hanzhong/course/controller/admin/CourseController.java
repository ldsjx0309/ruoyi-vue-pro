package cn.iocoder.yudao.module.hanzhong.course.controller.admin;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.course.controller.admin.vo.*;
import cn.iocoder.yudao.module.hanzhong.course.convert.CourseConvert;
import cn.iocoder.yudao.module.hanzhong.course.dal.dataobject.CourseDO;
import cn.iocoder.yudao.module.hanzhong.course.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 管理后台 - 汉中 课程管理
 *
 * @author hanzhong
 */
@Tag(name = "管理后台 - 汉中 课程管理")
@RestController
@RequestMapping("/hanzhong/course")
@Validated
public class CourseController {

    @Resource
    private CourseService courseService;

    @PostMapping("/create")
    @Operation(summary = "创建课程")
    @PreAuthorize("@ss.hasPermission('hanzhong:course:create')")
    public CommonResult<Long> createCourse(@Valid @RequestBody CourseCreateReqVO createReqVO) {
        return success(courseService.createCourse(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新课程")
    @PreAuthorize("@ss.hasPermission('hanzhong:course:update')")
    public CommonResult<Boolean> updateCourse(@Valid @RequestBody CourseUpdateReqVO updateReqVO) {
        courseService.updateCourse(updateReqVO);
        return success(true);
    }

    @PutMapping("/update-status")
    @Operation(summary = "更新课程状态")
    @PreAuthorize("@ss.hasPermission('hanzhong:course:update')")
    public CommonResult<Boolean> updateCourseStatus(@Valid @RequestBody CourseUpdateStatusReqVO updateReqVO) {
        courseService.updateCourseStatus(updateReqVO.getId(), updateReqVO.getStatus());
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除课程")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('hanzhong:course:delete')")
    public CommonResult<Boolean> deleteCourse(@RequestParam("id") Long id) {
        courseService.deleteCourse(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得课程详情")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hanzhong:course:query')")
    public CommonResult<CourseRespVO> getCourse(@RequestParam("id") Long id) {
        CourseDO course = courseService.getCourse(id);
        return success(CourseConvert.INSTANCE.convert(course));
    }

    @GetMapping("/page")
    @Operation(summary = "获得课程分页")
    @PreAuthorize("@ss.hasPermission('hanzhong:course:query')")
    public CommonResult<PageResult<CourseRespVO>> getCoursePage(@Valid CoursePageReqVO pageVO) {
        PageResult<CourseDO> pageResult = courseService.getCoursePage(pageVO);
        return success(CourseConvert.INSTANCE.convertPage(pageResult));
    }

}
