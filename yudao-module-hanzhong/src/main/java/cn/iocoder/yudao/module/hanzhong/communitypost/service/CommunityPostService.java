package cn.iocoder.yudao.module.hanzhong.communitypost.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.communitypost.controller.admin.vo.CommunityPostPageReqVO;
import cn.iocoder.yudao.module.hanzhong.communitypost.controller.app.vo.AppCommunityPostCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.communitypost.controller.app.vo.AppCommunityPostPageReqVO;
import cn.iocoder.yudao.module.hanzhong.communitypost.dal.dataobject.CommunityPostDO;

/**
 * 汉中 社区帖子 Service 接口
 *
 * @author hanzhong
 */
public interface CommunityPostService {

    /**
     * 创建帖子
     */
    Long createPost(Long userId, AppCommunityPostCreateReqVO createReqVO);

    /**
     * 更新帖子状态（管理员）
     */
    void updatePostStatus(Long id, Integer status);

    /**
     * 删除帖子（作者或管理员）
     */
    void deletePost(Long id, Long userId);

    /**
     * 获得帖子
     */
    CommunityPostDO getPost(Long id);

    /**
     * 获得帖子分页（管理员）
     */
    PageResult<CommunityPostDO> getPostPage(CommunityPostPageReqVO pageReqVO);

    /**
     * 获得帖子分页（App 公开）
     */
    PageResult<CommunityPostDO> getPostPageForApp(AppCommunityPostPageReqVO pageReqVO);

    /**
     * 获得我的帖子分页（登录用户）
     */
    PageResult<CommunityPostDO> getMyPostPage(AppCommunityPostPageReqVO pageReqVO, Long userId);

}
