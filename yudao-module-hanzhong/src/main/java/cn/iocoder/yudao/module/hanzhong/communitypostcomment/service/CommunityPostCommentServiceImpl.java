package cn.iocoder.yudao.module.hanzhong.communitypostcomment.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.communitypost.dal.dataobject.CommunityPostDO;
import cn.iocoder.yudao.module.hanzhong.communitypost.dal.mysql.CommunityPostMapper;
import cn.iocoder.yudao.module.hanzhong.communitypostcomment.controller.admin.vo.CommunityPostCommentPageReqVO;
import cn.iocoder.yudao.module.hanzhong.communitypostcomment.controller.app.vo.AppCommunityPostCommentCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.communitypostcomment.controller.app.vo.AppCommunityPostCommentPageReqVO;
import cn.iocoder.yudao.module.hanzhong.communitypostcomment.dal.dataobject.CommunityPostCommentDO;
import cn.iocoder.yudao.module.hanzhong.communitypostcomment.dal.mysql.CommunityPostCommentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.hanzhong.enums.ErrorCodeConstants.COMMUNITY_POST_COMMENT_NOT_EXISTS;
import static cn.iocoder.yudao.module.hanzhong.enums.ErrorCodeConstants.COMMUNITY_POST_COMMENT_NOT_YOURS;
import static cn.iocoder.yudao.module.hanzhong.enums.ErrorCodeConstants.COMMUNITY_POST_NOT_EXISTS;

/**
 * 汉中 社区帖子评论 Service 实现类
 *
 * @author hanzhong
 */
@Service
@Validated
public class CommunityPostCommentServiceImpl implements CommunityPostCommentService {

    @Resource
    private CommunityPostCommentMapper commentMapper;

    @Resource
    private CommunityPostMapper postMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createComment(Long userId, AppCommunityPostCommentCreateReqVO createReqVO) {
        CommunityPostDO post = postMapper.selectById(createReqVO.getPostId());
        if (post == null) {
            throw exception(COMMUNITY_POST_NOT_EXISTS);
        }
        CommunityPostCommentDO comment = new CommunityPostCommentDO();
        comment.setPostId(createReqVO.getPostId());
        comment.setUserId(userId);
        comment.setContent(createReqVO.getContent());
        comment.setParentId(createReqVO.getParentId() != null ? createReqVO.getParentId() : 0L);
        comment.setStatus(0);
        commentMapper.insert(comment);
        // 同步更新帖子 commentCount
        try {
            CommunityPostDO updatePost = new CommunityPostDO();
            updatePost.setId(createReqVO.getPostId());
            updatePost.setCommentCount((int) commentMapper.countByPostId(createReqVO.getPostId()));
            postMapper.updateById(updatePost);
        } catch (Exception ignored) {
            // 评论数统计失败不影响主流程
        }
        return comment.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteComment(Long id, Long userId) {
        CommunityPostCommentDO comment = commentMapper.selectById(id);
        if (comment == null) {
            throw exception(COMMUNITY_POST_COMMENT_NOT_EXISTS);
        }
        if (!userId.equals(comment.getUserId())) {
            throw exception(COMMUNITY_POST_COMMENT_NOT_YOURS);
        }
        commentMapper.deleteById(id);
        // 同步更新帖子 commentCount
        try {
            CommunityPostDO updatePost = new CommunityPostDO();
            updatePost.setId(comment.getPostId());
            updatePost.setCommentCount((int) commentMapper.countByPostId(comment.getPostId()));
            postMapper.updateById(updatePost);
        } catch (Exception ignored) {
            // 评论数统计失败不影响主流程
        }
    }

    @Override
    public void updateCommentStatus(Long id, Integer status) {
        if (commentMapper.selectById(id) == null) {
            throw exception(COMMUNITY_POST_COMMENT_NOT_EXISTS);
        }
        CommunityPostCommentDO updateObj = new CommunityPostCommentDO();
        updateObj.setId(id);
        updateObj.setStatus(status);
        commentMapper.updateById(updateObj);
    }

    @Override
    public PageResult<CommunityPostCommentDO> getCommentPage(AppCommunityPostCommentPageReqVO pageReqVO) {
        return commentMapper.selectPageByPostId(pageReqVO);
    }

    @Override
    public PageResult<CommunityPostCommentDO> getAdminCommentPage(CommunityPostCommentPageReqVO pageReqVO) {
        return commentMapper.selectPage(pageReqVO);
    }

}
