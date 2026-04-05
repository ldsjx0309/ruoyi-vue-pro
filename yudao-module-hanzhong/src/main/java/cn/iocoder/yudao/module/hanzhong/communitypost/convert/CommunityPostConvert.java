package cn.iocoder.yudao.module.hanzhong.communitypost.convert;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.communitypost.controller.admin.vo.CommunityPostRespVO;
import cn.iocoder.yudao.module.hanzhong.communitypost.controller.app.vo.AppCommunityPostCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.communitypost.controller.app.vo.AppCommunityPostRespVO;
import cn.iocoder.yudao.module.hanzhong.communitypost.dal.dataobject.CommunityPostDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 汉中 社区帖子 Convert
 *
 * @author hanzhong
 */
@Mapper
public interface CommunityPostConvert {

    CommunityPostConvert INSTANCE = Mappers.getMapper(CommunityPostConvert.class);

    CommunityPostDO convert(AppCommunityPostCreateReqVO createReqVO);

    CommunityPostRespVO convert(CommunityPostDO post);

    PageResult<CommunityPostRespVO> convertPage(PageResult<CommunityPostDO> pageResult);

    AppCommunityPostRespVO convertApp(CommunityPostDO post);

    List<AppCommunityPostRespVO> convertAppList(List<CommunityPostDO> list);

    PageResult<AppCommunityPostRespVO> convertAppPage(PageResult<CommunityPostDO> pageResult);

}
