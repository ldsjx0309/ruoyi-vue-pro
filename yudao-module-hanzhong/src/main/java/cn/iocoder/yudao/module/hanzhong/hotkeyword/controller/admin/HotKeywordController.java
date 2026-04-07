package cn.iocoder.yudao.module.hanzhong.hotkeyword.controller.admin;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.hotkeyword.controller.admin.vo.HotKeywordPageReqVO;
import cn.iocoder.yudao.module.hanzhong.hotkeyword.controller.admin.vo.HotKeywordRespVO;
import cn.iocoder.yudao.module.hanzhong.hotkeyword.controller.admin.vo.HotKeywordSaveReqVO;
import cn.iocoder.yudao.module.hanzhong.hotkeyword.service.HotKeywordService;
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
 * 管理后台 - 汉中 热门搜索关键词管理
 *
 * @author hanzhong
 */
@Tag(name = "管理后台 - 汉中 热门搜索关键词管理")
@RestController
@RequestMapping("/hanzhong/hot-keyword")
@Validated
public class HotKeywordController {

    @Resource
    private HotKeywordService hotKeywordService;

    @PostMapping("/create")
    @Operation(summary = "创建热门搜索关键词")
    @PreAuthorize("@ss.hasPermission('hanzhong:hot-keyword:create')")
    public CommonResult<Long> createHotKeyword(@Valid @RequestBody HotKeywordSaveReqVO saveReqVO) {
        return success(hotKeywordService.createHotKeyword(saveReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新热门搜索关键词")
    @PreAuthorize("@ss.hasPermission('hanzhong:hot-keyword:update')")
    public CommonResult<Boolean> updateHotKeyword(@Valid @RequestBody HotKeywordSaveReqVO saveReqVO) {
        hotKeywordService.updateHotKeyword(saveReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除热门搜索关键词")
    @Parameter(name = "id", description = "编号", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('hanzhong:hot-keyword:delete')")
    public CommonResult<Boolean> deleteHotKeyword(@RequestParam("id") Long id) {
        hotKeywordService.deleteHotKeyword(id);
        return success(true);
    }

    @GetMapping("/page")
    @Operation(summary = "获得热门搜索关键词分页")
    @PreAuthorize("@ss.hasPermission('hanzhong:hot-keyword:query')")
    public CommonResult<PageResult<HotKeywordRespVO>> getHotKeywordPage(@Valid HotKeywordPageReqVO pageVO) {
        return success(hotKeywordService.getHotKeywordPage(pageVO));
    }

}
