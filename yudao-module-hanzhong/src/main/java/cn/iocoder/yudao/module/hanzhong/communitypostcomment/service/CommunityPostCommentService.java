package cn.iocoder.yudao.module.hanzhong.communitypostcomment.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.communitypostcomment.controller.admin.vo.CommunityPostCommentPageReqVO;
import cn.iocoder.yudao.module.hanzhong.communitypostcomment.controller.app.vo.AppCommunityPostCommentCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.communitypostcomment.controller.app.vo.AppCommunityPostCommentPageReqVO;
import cn.iocoder.yudao.module.hanzhong.communitypostcomment.dal.dataobject.CommunityPostCommentDO;

/**
 * 汉中 社区帖子评论 Service 接口
 *
 * @author hanzhong
 */
public interface CommunityPostCommentService {

    Long createComment(Long userId, AppCommunityPostCommentCreateReqVO createReqVO);

    void deleteComment(Long id, Long userId);

    void updateCommentStatus(Long id, Integer status);

    PageResult<CommunityPostCommentDO> getCommentPage(AppCommunityPostCommentPageReqVO pageReqVO);

    PageResult<CommunityPostCommentDO> getAdminCommentPage(CommunityPostCommentPageReqVO pageReqVO);

}
