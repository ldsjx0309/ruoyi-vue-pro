package cn.iocoder.yudao.module.hanzhong.coursecategory.controller.admin;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.coursecategory.controller.admin.vo.*;
import cn.iocoder.yudao.module.hanzhong.coursecategory.convert.CourseCategoryConvert;
import cn.iocoder.yudao.module.hanzhong.coursecategory.dal.dataobject.CourseCategoryDO;
import cn.iocoder.yudao.module.hanzhong.coursecategory.service.CourseCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 管理后台 - 汉中 课程分类管理
 *
 * @author hanzhong
 */
@Tag(name = "管理后台 - 汉中 课程分类管理")
@RestController
@RequestMapping("/hanzhong/course-category")
@Validated
public class CourseCategoryController {

    @Resource
    private CourseCategoryService courseCategoryService;

    @PostMapping("/create")
    @Operation(summary = "创建课程分类")
    @PreAuthorize("@ss.hasPermission('hanzhong:course-category:create')")
    public CommonResult<Long> createCourseCategory(@Valid @RequestBody CourseCategoryCreateReqVO createReqVO) {
        return success(courseCategoryService.createCourseCategory(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新课程分类")
    @PreAuthorize("@ss.hasPermission('hanzhong:course-category:update')")
    public CommonResult<Boolean> updateCourseCategory(@Valid @RequestBody CourseCategoryUpdateReqVO updateReqVO) {
        courseCategoryService.updateCourseCategory(updateReqVO);
        return success(true);
    }

    @PutMapping("/update-status")
    @Operation(summary = "更新课程分类状态")
    @PreAuthorize("@ss.hasPermission('hanzhong:course-category:update')")
    public CommonResult<Boolean> updateCourseCategoryStatus(@Valid @RequestBody CourseCategoryUpdateStatusReqVO updateReqVO) {
        courseCategoryService.updateCourseCategoryStatus(updateReqVO.getId(), updateReqVO.getStatus());
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除课程分类")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('hanzhong:course-category:delete')")
    public CommonResult<Boolean> deleteCourseCategory(@RequestParam("id") Long id) {
        courseCategoryService.deleteCourseCategory(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得课程分类详情")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hanzhong:course-category:query')")
    public CommonResult<CourseCategoryRespVO> getCourseCategory(@RequestParam("id") Long id) {
        CourseCategoryDO courseCategory = courseCategoryService.getCourseCategory(id);
        return success(CourseCategoryConvert.INSTANCE.convert(courseCategory));
    }

    @GetMapping("/page")
    @Operation(summary = "获得课程分类分页")
    @PreAuthorize("@ss.hasPermission('hanzhong:course-category:query')")
    public CommonResult<PageResult<CourseCategoryRespVO>> getCourseCategoryPage(@Valid CourseCategoryPageReqVO pageVO) {
        PageResult<CourseCategoryDO> pageResult = courseCategoryService.getCourseCategoryPage(pageVO);
        return success(CourseCategoryConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/list")
    @Operation(summary = "获得所有课程分类列表（用于下拉选择）")
    @PreAuthorize("@ss.hasPermission('hanzhong:course-category:query')")
    public CommonResult<List<CourseCategoryRespVO>> getCourseCategoryList() {
        List<CourseCategoryDO> list = courseCategoryService.getEnabledCourseCategoryList();
        return success(CourseCategoryConvert.INSTANCE.convertList(list));
    }

}
