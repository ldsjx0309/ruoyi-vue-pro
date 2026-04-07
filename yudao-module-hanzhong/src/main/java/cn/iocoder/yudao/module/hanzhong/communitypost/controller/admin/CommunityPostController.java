package cn.iocoder.yudao.module.hanzhong.communitypost.controller.admin;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.communitypost.controller.admin.vo.CommunityPostPageReqVO;
import cn.iocoder.yudao.module.hanzhong.communitypost.controller.admin.vo.CommunityPostRespVO;
import cn.iocoder.yudao.module.hanzhong.communitypost.controller.admin.vo.CommunityPostUpdateFeatureReqVO;
import cn.iocoder.yudao.module.hanzhong.communitypost.controller.admin.vo.CommunityPostUpdateStatusReqVO;
import cn.iocoder.yudao.module.hanzhong.communitypost.convert.CommunityPostConvert;
import cn.iocoder.yudao.module.hanzhong.communitypost.dal.dataobject.CommunityPostDO;
import cn.iocoder.yudao.module.hanzhong.communitypost.service.CommunityPostService;
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
 * 管理后台 - 汉中 社区帖子管理
 *
 * @author hanzhong
 */
@Tag(name = "管理后台 - 汉中 社区帖子管理")
@RestController
@RequestMapping("/hanzhong/community-post")
@Validated
public class CommunityPostController {

    @Resource
    private CommunityPostService communityPostService;

    @PutMapping("/update-status")
    @Operation(summary = "更新社区帖子状态")
    @PreAuthorize("@ss.hasPermission('hanzhong:community-post:update')")
    public CommonResult<Boolean> updatePostStatus(@Valid @RequestBody CommunityPostUpdateStatusReqVO updateReqVO) {
        communityPostService.updatePostStatus(updateReqVO.getId(), updateReqVO.getStatus());
        return success(true);
    }

    @PutMapping("/update-feature")
    @Operation(summary = "更新社区帖子置顶/精华属性")
    @PreAuthorize("@ss.hasPermission('hanzhong:community-post:update')")
    public CommonResult<Boolean> updatePostFeature(@Valid @RequestBody CommunityPostUpdateFeatureReqVO reqVO) {
        communityPostService.updatePostFeatures(reqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除社区帖子")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('hanzhong:community-post:delete')")
    public CommonResult<Boolean> deletePost(@RequestParam("id") Long id) {
        communityPostService.deletePost(id, null);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得社区帖子详情")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hanzhong:community-post:query')")
    public CommonResult<CommunityPostRespVO> getPost(@RequestParam("id") Long id) {
        CommunityPostDO post = communityPostService.getPost(id);
        return success(CommunityPostConvert.INSTANCE.convert(post));
    }

    @GetMapping("/page")
    @Operation(summary = "获得社区帖子分页")
    @PreAuthorize("@ss.hasPermission('hanzhong:community-post:query')")
    public CommonResult<PageResult<CommunityPostRespVO>> getPostPage(@Valid CommunityPostPageReqVO pageVO) {
        PageResult<CommunityPostDO> pageResult = communityPostService.getPostPage(pageVO);
        return success(CommunityPostConvert.INSTANCE.convertPage(pageResult));
    }

}
