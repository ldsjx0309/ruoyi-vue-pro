package cn.iocoder.yudao.module.hanzhong.communitypost.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.communitypost.controller.admin.vo.CommunityPostPageReqVO;
import cn.iocoder.yudao.module.hanzhong.communitypost.controller.app.vo.AppCommunityPostCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.communitypost.controller.app.vo.AppCommunityPostPageReqVO;
import cn.iocoder.yudao.module.hanzhong.communitypost.controller.app.vo.AppCommunityPostUpdateReqVO;
import cn.iocoder.yudao.module.hanzhong.communitypost.convert.CommunityPostConvert;
import cn.iocoder.yudao.module.hanzhong.communitypost.dal.dataobject.CommunityPostDO;
import cn.iocoder.yudao.module.hanzhong.communitypost.dal.dataobject.CommunityPostLikeDO;
import cn.iocoder.yudao.module.hanzhong.communitypost.dal.mysql.CommunityPostLikeMapper;
import cn.iocoder.yudao.module.hanzhong.communitypost.dal.mysql.CommunityPostMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.hanzhong.enums.ErrorCodeConstants.COMMUNITY_POST_NOT_EXISTS;
import static cn.iocoder.yudao.module.hanzhong.enums.ErrorCodeConstants.COMMUNITY_POST_NOT_YOURS;

/**
 * 汉中 社区帖子 Service 实现类
 *
 * @author hanzhong
 */
@Service
@Validated
public class CommunityPostServiceImpl implements CommunityPostService {

    @Resource
    private CommunityPostMapper communityPostMapper;

    @Resource
    private CommunityPostLikeMapper communityPostLikeMapper;

    @Override
    public Long createPost(Long userId, AppCommunityPostCreateReqVO createReqVO) {
        CommunityPostDO post = CommunityPostConvert.INSTANCE.convert(createReqVO);
        post.setUserId(userId);
        post.setStatus(0);
        post.setViewCount(0);
        post.setLikeCount(0);
        post.setCommentCount(0);
        communityPostMapper.insert(post);
        return post.getId();
    }

    @Override
    public void updatePost(Long userId, AppCommunityPostUpdateReqVO updateReqVO) {
        CommunityPostDO post = communityPostMapper.selectById(updateReqVO.getId());
        if (post == null) {
            throw exception(COMMUNITY_POST_NOT_EXISTS);
        }
        // 仅作者本人可以编辑
        if (!userId.equals(post.getUserId())) {
            throw exception(COMMUNITY_POST_NOT_YOURS);
        }
        CommunityPostDO updateObj = new CommunityPostDO();
        updateObj.setId(updateReqVO.getId());
        updateObj.setTitle(updateReqVO.getTitle());
        updateObj.setContent(updateReqVO.getContent());
        updateObj.setCoverUrl(updateReqVO.getCoverUrl());
        updateObj.setCategory(updateReqVO.getCategory());
        communityPostMapper.updateById(updateObj);
    }

    @Override
    public void updatePostStatus(Long id, Integer status) {
        validatePostExists(id);
        CommunityPostDO updateObj = new CommunityPostDO();
        updateObj.setId(id);
        updateObj.setStatus(status);
        communityPostMapper.updateById(updateObj);
    }

    @Override
    public void deletePost(Long id, Long userId) {
        CommunityPostDO post = communityPostMapper.selectById(id);
        if (post == null) {
            throw exception(COMMUNITY_POST_NOT_EXISTS);
        }
        // 有 userId 时校验所有权（App 端调用）；userId 为 null 时为管理员强删
        if (userId != null && !userId.equals(post.getUserId())) {
            throw exception(COMMUNITY_POST_NOT_YOURS);
        }
        communityPostMapper.deleteById(id);
    }

    @Override
    public CommunityPostDO getPost(Long id) {
        return communityPostMapper.selectById(id);
    }

    @Override
    public void incrementViewCount(Long id) {
        communityPostMapper.incrementViewCount(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean toggleLike(Long userId, Long postId) {
        if (communityPostMapper.selectById(postId) == null) {
            throw exception(COMMUNITY_POST_NOT_EXISTS);
        }
        CommunityPostLikeDO existing = communityPostLikeMapper.selectByUserIdAndPostId(userId, postId);
        if (existing != null) {
            // 已点赞 -> 取消点赞（物理删除，避免唯一键冲突）
            communityPostLikeMapper.deleteByUserIdAndPostId(userId, postId);
            communityPostMapper.decrementLikeCount(postId);
            return false;
        } else {
            // 未点赞 -> 点赞
            CommunityPostLikeDO like = new CommunityPostLikeDO();
            like.setUserId(userId);
            like.setPostId(postId);
            communityPostLikeMapper.insert(like);
            communityPostMapper.incrementLikeCount(postId);
            return true;
        }
    }

    @Override
    public boolean isLiked(Long userId, Long postId) {
        return communityPostLikeMapper.selectByUserIdAndPostId(userId, postId) != null;
    }

    @Override
    public PageResult<CommunityPostDO> getPostPage(CommunityPostPageReqVO pageReqVO) {
        return communityPostMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<CommunityPostDO> getPostPageForApp(AppCommunityPostPageReqVO pageReqVO) {
        return communityPostMapper.selectPageForApp(pageReqVO);
    }

    @Override
    public PageResult<CommunityPostDO> getMyPostPage(AppCommunityPostPageReqVO pageReqVO, Long userId) {
        return communityPostMapper.selectPageByUserId(pageReqVO, userId);
    }

    @Override
    public List<CommunityPostDO> getHotPostList(int limit) {
        return communityPostMapper.selectHotList(limit);
    }

    @Override
    public List<CommunityPostDO> getLatestPostList(int limit) {
        return communityPostMapper.selectLatestList(limit);
    }

    private void validatePostExists(Long id) {
        if (communityPostMapper.selectById(id) == null) {
            throw exception(COMMUNITY_POST_NOT_EXISTS);
        }
    }

}
