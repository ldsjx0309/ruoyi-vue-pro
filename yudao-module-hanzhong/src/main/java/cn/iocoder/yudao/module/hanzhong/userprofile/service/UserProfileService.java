package cn.iocoder.yudao.module.hanzhong.userprofile.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.userprofile.controller.admin.vo.UserProfilePageReqVO;
import cn.iocoder.yudao.module.hanzhong.userprofile.controller.app.vo.AppUserProfileSaveReqVO;
import cn.iocoder.yudao.module.hanzhong.userprofile.dal.dataobject.UserProfileDO;

/**
 * 汉中 用户档案 Service 接口
 *
 * @author hanzhong
 */
public interface UserProfileService {

    /**
     * 创建或更新我的档案
     */
    UserProfileDO createOrUpdateMyProfile(Long userId, AppUserProfileSaveReqVO saveReqVO);

    /**
     * 更新档案状态
     */
    void updateUserProfileStatus(Long id, Integer status);

    /**
     * 获得档案
     */
    UserProfileDO getUserProfile(Long id);

    /**
     * 获得我的档案
     */
    UserProfileDO getMyProfile(Long userId);

    /**
     * 获得档案分页
     */
    PageResult<UserProfileDO> getUserProfilePage(UserProfilePageReqVO pageReqVO);

    /**
     * 删除用户档案（管理员操作）
     *
     * @param id 档案编号
     */
    void deleteUserProfile(Long id);

}
