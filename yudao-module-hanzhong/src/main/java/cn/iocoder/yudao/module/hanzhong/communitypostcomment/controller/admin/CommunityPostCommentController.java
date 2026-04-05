package cn.iocoder.yudao.module.hanzhong.communitypostcomment.controller.admin;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.communitypostcomment.controller.admin.vo.CommunityPostCommentPageReqVO;
import cn.iocoder.yudao.module.hanzhong.communitypostcomment.controller.admin.vo.CommunityPostCommentRespVO;
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
import javax.validation.Valid;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 管理后台 - 汉中 社区帖子评论
 *
 * @author hanzhong
 */
@Tag(name = "管理后台 - 汉中 社区帖子评论")
@RestController
@RequestMapping("/hanzhong/community-post-comment")
@Validated
public class CommunityPostCommentController {

    @Resource
    private CommunityPostCommentService commentService;

    @GetMapping("/page")
    @Operation(summary = "获取评论分页")
    @PreAuthorize("@ss.hasPermission('hanzhong:community-post-comment:query')")
    public CommonResult<PageResult<CommunityPostCommentRespVO>> getCommentPage(@Valid CommunityPostCommentPageReqVO pageReqVO) {
        PageResult<CommunityPostCommentDO> pageResult = commentService.getAdminCommentPage(pageReqVO);
        return success(CommunityPostCommentConvert.INSTANCE.convertPage(pageResult));
    }

    @PutMapping("/update-status")
    @Operation(summary = "修改评论状态（屏蔽/恢复）")
    @PreAuthorize("@ss.hasPermission('hanzhong:community-post-comment:update')")
    public CommonResult<Boolean> updateCommentStatus(@RequestParam("id") Long id,
                                                     @RequestParam("status") Integer status) {
        commentService.updateCommentStatus(id, status);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除评论（管理员）")
    @Parameter(name = "id", description = "评论编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hanzhong:community-post-comment:delete')")
    public CommonResult<Boolean> deleteComment(@RequestParam("id") Long id) {
        // 管理员删除：绕过所有者校验，直接删除
        commentService.updateCommentStatus(id, 1);
        return success(true);
    }

}
