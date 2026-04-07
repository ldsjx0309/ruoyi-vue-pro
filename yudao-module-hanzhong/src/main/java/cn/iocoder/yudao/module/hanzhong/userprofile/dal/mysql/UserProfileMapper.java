package cn.iocoder.yudao.module.hanzhong.userprofile.dal.mysql;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.hanzhong.userprofile.controller.admin.vo.UserProfilePageReqVO;
import cn.iocoder.yudao.module.hanzhong.userprofile.dal.dataobject.UserProfileDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 汉中 用户档案 Mapper
 *
 * @author hanzhong
 */
@Mapper
public interface UserProfileMapper extends BaseMapperX<UserProfileDO> {

    default PageResult<UserProfileDO> selectPage(UserProfilePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UserProfileDO>()
                .eqIfPresent(UserProfileDO::getUserId, reqVO.getUserId())
                .likeIfPresent(UserProfileDO::getUsername, reqVO.getUsername())
                .eqIfPresent(UserProfileDO::getGender, reqVO.getGender())
                .eqIfPresent(UserProfileDO::getPreferredLanguage, reqVO.getPreferredLanguage())
                .eqIfPresent(UserProfileDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(UserProfileDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(UserProfileDO::getCreateTime));
    }

    default UserProfileDO selectByUserId(Long userId) {
        return selectOne(UserProfileDO::getUserId, userId);
    }

}
