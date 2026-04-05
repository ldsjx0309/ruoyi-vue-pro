package cn.iocoder.yudao.module.hanzhong.communitypost.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.communitypost.controller.admin.vo.CommunityPostPageReqVO;
import cn.iocoder.yudao.module.hanzhong.communitypost.controller.app.vo.AppCommunityPostCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.communitypost.controller.app.vo.AppCommunityPostPageReqVO;
import cn.iocoder.yudao.module.hanzhong.communitypost.convert.CommunityPostConvert;
import cn.iocoder.yudao.module.hanzhong.communitypost.dal.dataobject.CommunityPostDO;
import cn.iocoder.yudao.module.hanzhong.communitypost.dal.mysql.CommunityPostMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.hanzhong.enums.ErrorCodeConstants.COMMUNITY_POST_NOT_EXISTS;

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
    public void updatePostStatus(Long id, Integer status) {
        validatePostExists(id);
        CommunityPostDO updateObj = new CommunityPostDO();
        updateObj.setId(id);
        updateObj.setStatus(status);
        communityPostMapper.updateById(updateObj);
    }

    @Override
    public void deletePost(Long id, Long userId) {
        validatePostExists(id);
        communityPostMapper.deleteById(id);
    }

    @Override
    public CommunityPostDO getPost(Long id) {
        return communityPostMapper.selectById(id);
    }

    @Override
    public PageResult<CommunityPostDO> getPostPage(CommunityPostPageReqVO pageReqVO) {
        return communityPostMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<CommunityPostDO> getPostPageForApp(AppCommunityPostPageReqVO pageReqVO) {
        return communityPostMapper.selectPageForApp(pageReqVO);
    }

    private void validatePostExists(Long id) {
        if (communityPostMapper.selectById(id) == null) {
            throw exception(COMMUNITY_POST_NOT_EXISTS);
        }
    }

}
