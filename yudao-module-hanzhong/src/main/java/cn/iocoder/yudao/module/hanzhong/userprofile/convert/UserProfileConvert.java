package cn.iocoder.yudao.module.hanzhong.userprofile.convert;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.userprofile.controller.admin.vo.UserProfileRespVO;
import cn.iocoder.yudao.module.hanzhong.userprofile.controller.app.vo.AppUserProfileRespVO;
import cn.iocoder.yudao.module.hanzhong.userprofile.controller.app.vo.AppUserProfileSaveReqVO;
import cn.iocoder.yudao.module.hanzhong.userprofile.dal.dataobject.UserProfileDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 汉中 用户档案 Convert
 *
 * @author hanzhong
 */
@Mapper
public interface UserProfileConvert {

    UserProfileConvert INSTANCE = Mappers.getMapper(UserProfileConvert.class);

    UserProfileDO convert(AppUserProfileSaveReqVO saveReqVO);

    UserProfileRespVO convert(UserProfileDO userProfile);

    PageResult<UserProfileRespVO> convertPage(PageResult<UserProfileDO> pageResult);

    AppUserProfileRespVO convertApp(UserProfileDO userProfile);

}
