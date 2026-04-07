package cn.iocoder.yudao.module.hanzhong.courserating.controller.admin;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.courserating.controller.admin.vo.CourseRatingPageReqVO;
import cn.iocoder.yudao.module.hanzhong.courserating.controller.admin.vo.CourseRatingRespVO;
import cn.iocoder.yudao.module.hanzhong.courserating.convert.CourseRatingConvert;
import cn.iocoder.yudao.module.hanzhong.courserating.dal.dataobject.CourseRatingDO;
import cn.iocoder.yudao.module.hanzhong.courserating.service.CourseRatingService;
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
 * 管理后台 - 汉中 课程评分管理
 *
 * @author hanzhong
 */
@Tag(name = "管理后台 - 汉中 课程评分管理")
@RestController
@RequestMapping("/hanzhong/course-rating")
@Validated
public class CourseRatingController {

    @Resource
    private CourseRatingService courseRatingService;

    @GetMapping("/page")
    @Operation(summary = "获得课程评分分页")
    @PreAuthorize("@ss.hasPermission('hanzhong:course-rating:query')")
    public CommonResult<PageResult<CourseRatingRespVO>> getRatingPage(@Valid CourseRatingPageReqVO pageVO) {
        PageResult<CourseRatingDO> pageResult = courseRatingService.getRatingAdminPage(pageVO);
        return success(CourseRatingConvert.INSTANCE.convertPage(pageResult));
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除课程评分（管理员）")
    @Parameter(name = "id", description = "评分编号", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('hanzhong:course-rating:delete')")
    public CommonResult<Boolean> deleteRating(@RequestParam("id") Long id) {
        courseRatingService.deleteRating(id);
        return success(true);
    }

}
