package cn.iocoder.yudao.module.hanzhong.userprofile.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.userprofile.controller.admin.vo.UserProfilePageReqVO;
import cn.iocoder.yudao.module.hanzhong.userprofile.controller.app.vo.AppUserProfileSaveReqVO;
import cn.iocoder.yudao.module.hanzhong.userprofile.convert.UserProfileConvert;
import cn.iocoder.yudao.module.hanzhong.userprofile.dal.dataobject.UserProfileDO;
import cn.iocoder.yudao.module.hanzhong.userprofile.dal.mysql.UserProfileMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.hanzhong.enums.ErrorCodeConstants.USER_PROFILE_NOT_EXISTS;

/**
 * 汉中 用户档案 Service 实现类
 *
 * @author hanzhong
 */
@Service
@Validated
public class UserProfileServiceImpl implements UserProfileService {

    @Resource
    private UserProfileMapper userProfileMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserProfileDO createOrUpdateMyProfile(Long userId, AppUserProfileSaveReqVO saveReqVO) {
        UserProfileDO existing = userProfileMapper.selectByUserId(userId);
        if (existing == null) {
            UserProfileDO profile = UserProfileConvert.INSTANCE.convert(saveReqVO);
            profile.setUserId(userId);
            profile.setStatus(0);
            userProfileMapper.insert(profile);
            return profile;
        } else {
            UserProfileDO updateObj = UserProfileConvert.INSTANCE.convert(saveReqVO);
            updateObj.setId(existing.getId());
            userProfileMapper.updateById(updateObj);
            return userProfileMapper.selectById(existing.getId());
        }
    }

    @Override
    public void updateUserProfileStatus(Long id, Integer status) {
        validateUserProfileExists(id);
        UserProfileDO updateObj = new UserProfileDO();
        updateObj.setId(id);
        updateObj.setStatus(status);
        userProfileMapper.updateById(updateObj);
    }

    @Override
    public UserProfileDO getUserProfile(Long id) {
        return userProfileMapper.selectById(id);
    }

    @Override
    public UserProfileDO getMyProfile(Long userId) {
        return userProfileMapper.selectByUserId(userId);
    }

    @Override
    public PageResult<UserProfileDO> getUserProfilePage(UserProfilePageReqVO pageReqVO) {
        return userProfileMapper.selectPage(pageReqVO);
    }

    private void validateUserProfileExists(Long id) {
        if (userProfileMapper.selectById(id) == null) {
            throw exception(USER_PROFILE_NOT_EXISTS);
        }
    }

}
