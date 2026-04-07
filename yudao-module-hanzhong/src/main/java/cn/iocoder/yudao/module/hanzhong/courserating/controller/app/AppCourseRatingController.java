package cn.iocoder.yudao.module.hanzhong.courserating.controller.app;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.hanzhong.courserating.controller.app.vo.AppCourseRatingCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.courserating.controller.app.vo.AppCourseRatingRespVO;
import cn.iocoder.yudao.module.hanzhong.courserating.service.CourseRatingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.validation.Valid;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 用户 APP - 汉中 课程评分
 *
 * @author hanzhong
 */
@RestController
@RequestMapping("/hanzhong/app/course-rating")
@Tag(name = "用户 APP - 汉中 课程评分")
@Validated
public class AppCourseRatingController {

    @Resource
    private CourseRatingService courseRatingService;

    @PostMapping("/create-or-update")
    @Operation(summary = "创建或更新课程评分（一个用户对同一课程只保留一条评分；付费课程需先购买）")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Long> createOrUpdateRating(@Valid @RequestBody AppCourseRatingCreateReqVO createReqVO) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        return success(courseRatingService.createOrUpdateRating(userId, createReqVO));
    }

    @GetMapping("/my-rating")
    @Operation(summary = "获取当前用户对指定课程的评分（未评分返回 null）")
    @Parameter(name = "courseId", description = "课程编号", required = true, example = "1024")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<AppCourseRatingRespVO> getMyRating(@RequestParam("courseId") Long courseId) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        return success(courseRatingService.getMyRating(userId, courseId));
    }

    @GetMapping("/page")
    @Operation(summary = "获取课程评分分页列表（公开，任意用户可查看）")
    @Parameter(name = "courseId", description = "课程编号", required = true, example = "1024")
    @PermitAll
    public CommonResult<PageResult<AppCourseRatingRespVO>> getRatingPage(
            @RequestParam("courseId") Long courseId,
            @Valid PageParam pageParam) {
        return success(courseRatingService.getRatingPageByCourseId(pageParam, courseId));
    }

}
