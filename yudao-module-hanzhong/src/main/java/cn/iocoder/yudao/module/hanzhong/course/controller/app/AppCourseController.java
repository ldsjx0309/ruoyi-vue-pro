package cn.iocoder.yudao.module.hanzhong.course.controller.app;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.hanzhong.course.controller.app.vo.AppCoursePageReqVO;
import cn.iocoder.yudao.module.hanzhong.course.controller.app.vo.AppCourseRespVO;
import cn.iocoder.yudao.module.hanzhong.course.convert.CourseConvert;
import cn.iocoder.yudao.module.hanzhong.course.dal.dataobject.CourseDO;
import cn.iocoder.yudao.module.hanzhong.course.service.CourseService;
import cn.iocoder.yudao.module.hanzhong.courseorder.dal.mysql.CourseOrderMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.validation.Valid;

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

    @GetMapping("/page")
    @Operation(summary = "获取课程分页列表")
    @PermitAll
    public CommonResult<PageResult<AppCourseRespVO>> getCoursePageForApp(@Valid AppCoursePageReqVO pageReqVO) {
        PageResult<CourseDO> pageResult = courseService.getCoursePageForApp(pageReqVO);
        return success(CourseConvert.INSTANCE.convertAppPage(pageResult));
    }

    @GetMapping("/get")
    @Operation(summary = "获取课程详情")
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
        // 如果用户已登录，设置是否已购买标志
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        if (loginUserId != null) {
            respVO.setHasPurchased(courseOrderMapper.selectActiveByUserIdAndCourseId(loginUserId, id) != null);
        }
        return success(respVO);
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
