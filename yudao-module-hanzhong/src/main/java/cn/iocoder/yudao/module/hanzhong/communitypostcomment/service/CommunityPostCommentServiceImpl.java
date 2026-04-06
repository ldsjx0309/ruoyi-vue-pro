package cn.iocoder.yudao.module.hanzhong.communitypostcomment.service;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.communitypost.dal.dataobject.CommunityPostDO;
import cn.iocoder.yudao.module.hanzhong.communitypost.dal.mysql.CommunityPostMapper;
import cn.iocoder.yudao.module.hanzhong.communitypostcomment.controller.admin.vo.CommunityPostCommentPageReqVO;
import cn.iocoder.yudao.module.hanzhong.communitypostcomment.controller.app.vo.AppCommunityPostCommentCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.communitypostcomment.controller.app.vo.AppCommunityPostCommentPageReqVO;
import cn.iocoder.yudao.module.hanzhong.communitypostcomment.dal.dataobject.CommunityPostCommentDO;
import cn.iocoder.yudao.module.hanzhong.communitypostcomment.dal.mysql.CommunityPostCommentMapper;
import cn.iocoder.yudao.module.hanzhong.message.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
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
@Slf4j
public class CommunityPostCommentServiceImpl implements CommunityPostCommentService {

    @Resource
    private CommunityPostCommentMapper commentMapper;

    @Resource
    private CommunityPostMapper postMapper;

    @Resource
    @Lazy
    private MessageService messageService;

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
        } catch (Exception e) {
            log.warn("[createComment] 更新帖子评论数失败, postId={}", createReqVO.getPostId(), e);
        }
        // 通知帖子作者（如果评论者不是作者本人）
        try {
            if (post.getUserId() != null && !post.getUserId().equals(userId)) {
                String title = "您的帖子收到新评论";
                String postTitle = post.getTitle() != null ? post.getTitle() : "您的帖子";
                String content = "您的帖子《" + postTitle + "》收到了新评论，快去看看吧！";
                messageService.sendSystemMessage(post.getUserId(), title, content);
            }
        } catch (Exception e) {
            log.warn("[createComment] 发送评论通知失败, postId={}", createReqVO.getPostId(), e);
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
        } catch (Exception e) {
            log.warn("[deleteComment] 更新帖子评论数失败, postId={}", comment.getPostId(), e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void adminDeleteComment(Long id) {
        CommunityPostCommentDO comment = commentMapper.selectById(id);
        if (comment == null) {
            throw exception(COMMUNITY_POST_COMMENT_NOT_EXISTS);
        }
        commentMapper.deleteById(id);
        // 同步更新帖子 commentCount
        try {
            CommunityPostDO updatePost = new CommunityPostDO();
            updatePost.setId(comment.getPostId());
            updatePost.setCommentCount((int) commentMapper.countByPostId(comment.getPostId()));
            postMapper.updateById(updatePost);
        } catch (Exception e) {
            log.warn("[adminDeleteComment] 更新帖子评论数失败, postId={}", comment.getPostId(), e);
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

    @Override
    public PageResult<CommunityPostCommentDO> getMyCommentPage(PageParam pageParam, Long userId) {
        return commentMapper.selectPageByUserId(pageParam, userId);
    }

}
