package cn.iocoder.yudao.module.hanzhong.coursefavorite.controller.app;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.hanzhong.coursefavorite.controller.app.vo.AppCourseFavoriteRespVO;
import cn.iocoder.yudao.module.hanzhong.coursefavorite.convert.CourseFavoriteConvert;
import cn.iocoder.yudao.module.hanzhong.coursefavorite.dal.dataobject.CourseFavoriteDO;
import cn.iocoder.yudao.module.hanzhong.coursefavorite.service.CourseFavoriteService;
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
 * 用户 APP - 汉中 课程收藏
 *
 * @author hanzhong
 */
@RestController
@RequestMapping("/hanzhong/app/course-favorite")
@Tag(name = "用户 APP - 汉中 课程收藏")
@Validated
public class AppCourseFavoriteController {

    @Resource
    private CourseFavoriteService courseFavoriteService;

    @PostMapping("/toggle")
    @Operation(summary = "收藏/取消收藏课程，返回当前是否已收藏")
    @Parameter(name = "courseId", description = "课程编号", required = true, example = "1024")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Boolean> toggleFavorite(@RequestParam("courseId") Long courseId) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        boolean favorited = courseFavoriteService.toggleFavorite(userId, courseId);
        return success(favorited);
    }

    @GetMapping("/is-favorited")
    @Operation(summary = "查询课程是否已收藏")
    @Parameter(name = "courseId", description = "课程编号", required = true, example = "1024")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Boolean> isFavorited(@RequestParam("courseId") Long courseId) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        return success(courseFavoriteService.isFavorited(userId, courseId));
    }

    @GetMapping("/my-page")
    @Operation(summary = "获取我的收藏课程分页")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<PageResult<AppCourseFavoriteRespVO>> getMyFavoritePage(@Valid PageParam pageParam) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        PageResult<CourseFavoriteDO> pageResult = courseFavoriteService.getMyFavoritePage(pageParam, userId);
        return success(CourseFavoriteConvert.INSTANCE.convertAppPage(pageResult));
    }

    @GetMapping("/favorited-course-ids")
    @Operation(summary = "获取我收藏的课程ID列表（用于列表页批量判断收藏状态）")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<java.util.List<Long>> getFavoritedCourseIds() {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        return success(courseFavoriteService.getFavoritedCourseIds(userId));
    }

}
