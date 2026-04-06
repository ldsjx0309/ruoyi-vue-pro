package cn.iocoder.yudao.module.hanzhong.communitypostcomment.controller.app;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.hanzhong.communitypostcomment.controller.app.vo.AppCommunityPostCommentCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.communitypostcomment.controller.app.vo.AppCommunityPostCommentPageReqVO;
import cn.iocoder.yudao.module.hanzhong.communitypostcomment.controller.app.vo.AppCommunityPostCommentRespVO;
import cn.iocoder.yudao.module.hanzhong.communitypostcomment.convert.CommunityPostCommentConvert;
import cn.iocoder.yudao.module.hanzhong.communitypostcomment.dal.dataobject.CommunityPostCommentDO;
import cn.iocoder.yudao.module.hanzhong.communitypostcomment.service.CommunityPostCommentService;
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
 * 用户 APP - 汉中 社区帖子评论
 *
 * @author hanzhong
 */
@RestController
@RequestMapping("/hanzhong/app/community-post-comment")
@Tag(name = "用户 APP - 汉中 社区帖子评论")
@Validated
public class AppCommunityPostCommentController {

    @Resource
    private CommunityPostCommentService commentService;

    @GetMapping("/page")
    @Operation(summary = "获取帖子评论分页（公开）")
    @PermitAll
    public CommonResult<PageResult<AppCommunityPostCommentRespVO>> getCommentPage(
            @Valid AppCommunityPostCommentPageReqVO pageReqVO) {
        PageResult<CommunityPostCommentDO> pageResult = commentService.getCommentPage(pageReqVO);
        return success(CommunityPostCommentConvert.INSTANCE.convertAppPage(pageResult));
    }

    @PostMapping("/create")
    @Operation(summary = "发布评论")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Long> createComment(@Valid @RequestBody AppCommunityPostCommentCreateReqVO createReqVO) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        return success(commentService.createComment(userId, createReqVO));
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除我的评论")
    @Parameter(name = "id", description = "评论编号", required = true, example = "1024")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Boolean> deleteComment(@RequestParam("id") Long id) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        commentService.deleteComment(id, userId);
        return success(true);
    }

    @GetMapping("/my-page")
    @Operation(summary = "获取我发布的评论分页")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<PageResult<AppCommunityPostCommentRespVO>> getMyCommentPage(@Valid PageParam pageParam) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        PageResult<CommunityPostCommentDO> pageResult = commentService.getMyCommentPage(pageParam, userId);
        return success(CommunityPostCommentConvert.INSTANCE.convertAppPage(pageResult));
    }

}
