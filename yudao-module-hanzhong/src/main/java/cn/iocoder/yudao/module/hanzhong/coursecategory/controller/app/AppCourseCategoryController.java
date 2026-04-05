package cn.iocoder.yudao.module.hanzhong.coursecategory.controller.app;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.hanzhong.coursecategory.controller.app.vo.AppCourseCategoryRespVO;
import cn.iocoder.yudao.module.hanzhong.coursecategory.convert.CourseCategoryConvert;
import cn.iocoder.yudao.module.hanzhong.coursecategory.dal.dataobject.CourseCategoryDO;
import cn.iocoder.yudao.module.hanzhong.coursecategory.service.CourseCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 用户 APP - 汉中 课程分类
 *
 * @author hanzhong
 */
@RestController
@RequestMapping("/hanzhong/app/course-category")
@Tag(name = "用户 APP - 汉中 课程分类")
@Validated
public class AppCourseCategoryController {

    @Resource
    private CourseCategoryService courseCategoryService;

    @GetMapping("/list")
    @Operation(summary = "获取课程分类列表（仅返回启用的分类）")
    @PermitAll
    public CommonResult<List<AppCourseCategoryRespVO>> getCourseCategoryList() {
        List<CourseCategoryDO> list = courseCategoryService.getEnabledCourseCategoryList();
        return success(CourseCategoryConvert.INSTANCE.convertAppList(list));
    }

}
