package cn.iocoder.yudao.module.hanzhong.communitypost.controller.app;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.hanzhong.communitypost.controller.app.vo.AppCommunityPostCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.communitypost.controller.app.vo.AppCommunityPostPageReqVO;
import cn.iocoder.yudao.module.hanzhong.communitypost.controller.app.vo.AppCommunityPostRespVO;
import cn.iocoder.yudao.module.hanzhong.communitypost.controller.app.vo.AppCommunityPostUpdateReqVO;
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
import javax.annotation.security.PermitAll;
import javax.validation.Valid;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 用户 APP - 汉中 社区帖子
 *
 * @author hanzhong
 */
@RestController
@RequestMapping("/hanzhong/app/community-post")
@Tag(name = "用户 APP - 汉中 社区帖子")
@Validated
public class AppCommunityPostController {

    @Resource
    private CommunityPostService communityPostService;

    @GetMapping("/page")
    @Operation(summary = "获取社区帖子分页列表（公开）")
    @PermitAll
    public CommonResult<PageResult<AppCommunityPostRespVO>> getPostPage(@Valid AppCommunityPostPageReqVO pageReqVO) {
        PageResult<CommunityPostDO> pageResult = communityPostService.getPostPageForApp(pageReqVO);
        PageResult<AppCommunityPostRespVO> voPage = CommunityPostConvert.INSTANCE.convertAppPage(pageResult);
        // 如果用户已登录，标记每条帖子是否已点赞
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        if (loginUserId != null) {
            voPage.getList().forEach(vo ->
                    vo.setIsLiked(communityPostService.isLiked(loginUserId, vo.getId())));
        }
        return success(voPage);
    }

    @GetMapping("/get")
    @Operation(summary = "获取社区帖子详情（公开）")
    @Parameter(name = "id", description = "帖子编号", required = true, example = "1024")
    @PermitAll
    public CommonResult<AppCommunityPostRespVO> getPost(@RequestParam("id") Long id) {
        CommunityPostDO post = communityPostService.getPost(id);
        if (post != null) {
            try {
                communityPostService.incrementViewCount(id);
            } catch (Exception ignored) {
                // 浏览量统计失败不影响主流程
            }
        }
        AppCommunityPostRespVO respVO = CommunityPostConvert.INSTANCE.convertApp(post);
        if (respVO != null) {
            Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
            if (loginUserId != null) {
                respVO.setIsLiked(communityPostService.isLiked(loginUserId, id));
            }
        }
        return success(respVO);
    }

    @PostMapping("/create")
    @Operation(summary = "发布社区帖子")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Long> createPost(@Valid @RequestBody AppCommunityPostCreateReqVO createReqVO) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        return success(communityPostService.createPost(userId, createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新我的帖子（仅作者本人可操作）")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Boolean> updatePost(@Valid @RequestBody AppCommunityPostUpdateReqVO updateReqVO) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        communityPostService.updatePost(userId, updateReqVO);
        return success(true);
    }

    @PostMapping("/like")
    @Operation(summary = "点赞 / 取消点赞帖子，返回当前是否已点赞")
    @Parameter(name = "id", description = "帖子编号", required = true, example = "1024")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Boolean> toggleLike(@RequestParam("id") Long id) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        boolean liked = communityPostService.toggleLike(userId, id);
        return success(liked);
    }

    @GetMapping("/my-page")
    @Operation(summary = "获取我发布的帖子分页")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<PageResult<AppCommunityPostRespVO>> getMyPostPage(@Valid AppCommunityPostPageReqVO pageReqVO) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        PageResult<CommunityPostDO> pageResult = communityPostService.getMyPostPage(pageReqVO, userId);
        return success(CommunityPostConvert.INSTANCE.convertAppPage(pageResult));
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除我的帖子")
    @Parameter(name = "id", description = "帖子编号", required = true, example = "1024")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Boolean> deletePost(@RequestParam("id") Long id) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        communityPostService.deletePost(id, userId);
        return success(true);
    }

}
