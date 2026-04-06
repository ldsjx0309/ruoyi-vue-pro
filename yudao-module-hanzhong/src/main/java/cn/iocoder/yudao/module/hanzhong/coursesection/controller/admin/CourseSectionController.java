package cn.iocoder.yudao.module.hanzhong.coursesection.controller.admin;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.coursesection.controller.admin.vo.CourseSectionCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.coursesection.controller.admin.vo.CourseSectionPageReqVO;
import cn.iocoder.yudao.module.hanzhong.coursesection.controller.admin.vo.CourseSectionRespVO;
import cn.iocoder.yudao.module.hanzhong.coursesection.controller.admin.vo.CourseSectionUpdateReqVO;
import cn.iocoder.yudao.module.hanzhong.coursesection.convert.CourseSectionConvert;
import cn.iocoder.yudao.module.hanzhong.coursesection.dal.dataobject.CourseSectionDO;
import cn.iocoder.yudao.module.hanzhong.coursesection.service.CourseSectionService;
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
 * 管理后台 - 汉中 课程章节
 *
 * @author hanzhong
 */
@Tag(name = "管理后台 - 汉中 课程章节")
@RestController
@RequestMapping("/hanzhong/course-section")
@Validated
public class CourseSectionController {

    @Resource
    private CourseSectionService courseSectionService;

    @PostMapping("/create")
    @Operation(summary = "创建课程章节")
    @PreAuthorize("@ss.hasPermission('hanzhong:course-section:create')")
    public CommonResult<Long> createSection(@Valid @RequestBody CourseSectionCreateReqVO createReqVO) {
        return success(courseSectionService.createSection(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新课程章节")
    @PreAuthorize("@ss.hasPermission('hanzhong:course-section:update')")
    public CommonResult<Boolean> updateSection(@Valid @RequestBody CourseSectionUpdateReqVO updateReqVO) {
        courseSectionService.updateSection(updateReqVO);
        return success(true);
    }

    @PutMapping("/update-status")
    @Operation(summary = "修改课程章节状态")
    @PreAuthorize("@ss.hasPermission('hanzhong:course-section:update')")
    public CommonResult<Boolean> updateSectionStatus(@RequestParam("id") Long id,
                                                     @RequestParam("status") Integer status) {
        courseSectionService.updateSectionStatus(id, status);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除课程章节")
    @Parameter(name = "id", description = "章节编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hanzhong:course-section:delete')")
    public CommonResult<Boolean> deleteSection(@RequestParam("id") Long id) {
        courseSectionService.deleteSection(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获取课程章节详情")
    @Parameter(name = "id", description = "章节编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hanzhong:course-section:query')")
    public CommonResult<CourseSectionRespVO> getSection(@RequestParam("id") Long id) {
        CourseSectionDO section = courseSectionService.getSection(id);
        return success(CourseSectionConvert.INSTANCE.convert(section));
    }

    @GetMapping("/page")
    @Operation(summary = "获取课程章节分页")
    @PreAuthorize("@ss.hasPermission('hanzhong:course-section:query')")
    public CommonResult<PageResult<CourseSectionRespVO>> getSectionPage(@Valid CourseSectionPageReqVO pageReqVO) {
        PageResult<CourseSectionDO> pageResult = courseSectionService.getSectionPage(pageReqVO);
        return success(CourseSectionConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/list")
    @Operation(summary = "根据课程编号获取章节列表（不分页，用于课程章节管理）")
    @Parameter(name = "courseId", description = "课程编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hanzhong:course-section:query')")
    public CommonResult<List<CourseSectionRespVO>> getSectionList(@RequestParam("courseId") Long courseId) {
        List<CourseSectionDO> sections = courseSectionService.getSectionsByCourseIdForAdmin(courseId);
        return success(CourseSectionConvert.INSTANCE.convertList(sections));
    }

}
