package cn.iocoder.yudao.module.hanzhong.coursefavorite.controller.admin;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.coursefavorite.controller.admin.vo.CourseFavoritePageReqVO;
import cn.iocoder.yudao.module.hanzhong.coursefavorite.controller.admin.vo.CourseFavoriteRespVO;
import cn.iocoder.yudao.module.hanzhong.coursefavorite.convert.CourseFavoriteConvert;
import cn.iocoder.yudao.module.hanzhong.coursefavorite.dal.dataobject.CourseFavoriteDO;
import cn.iocoder.yudao.module.hanzhong.coursefavorite.dal.mysql.CourseFavoriteMapper;
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
 * 管理后台 - 汉中 课程收藏
 *
 * @author hanzhong
 */
@Tag(name = "管理后台 - 汉中 课程收藏")
@RestController
@RequestMapping("/hanzhong/course-favorite")
@Validated
public class CourseFavoriteController {

    @Resource
    private CourseFavoriteMapper courseFavoriteMapper;

    @GetMapping("/page")
    @Operation(summary = "获取课程收藏分页列表")
    @PreAuthorize("@ss.hasPermission('hanzhong:course-favorite:query')")
    public CommonResult<PageResult<CourseFavoriteRespVO>> getCourseFavoritePage(@Valid CourseFavoritePageReqVO pageReqVO) {
        PageResult<CourseFavoriteDO> pageResult = courseFavoriteMapper.selectAdminPage(pageReqVO);
        return success(CourseFavoriteConvert.INSTANCE.convertAdminPage(pageResult));
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除课程收藏记录（管理员）")
    @Parameter(name = "id", description = "收藏编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hanzhong:course-favorite:delete')")
    public CommonResult<Boolean> deleteCourseFavorite(@RequestParam("id") Long id) {
        courseFavoriteMapper.deleteByIdPhysically(id);
        return success(true);
    }

}
