package cn.iocoder.yudao.module.hanzhong.communitypostcomment.convert;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.communitypostcomment.controller.admin.vo.CommunityPostCommentRespVO;
import cn.iocoder.yudao.module.hanzhong.communitypostcomment.controller.app.vo.AppCommunityPostCommentRespVO;
import cn.iocoder.yudao.module.hanzhong.communitypostcomment.dal.dataobject.CommunityPostCommentDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 汉中 社区帖子评论 Convert
 *
 * @author hanzhong
 */
@Mapper
public interface CommunityPostCommentConvert {

    CommunityPostCommentConvert INSTANCE = Mappers.getMapper(CommunityPostCommentConvert.class);

    AppCommunityPostCommentRespVO convertApp(CommunityPostCommentDO bean);

    PageResult<AppCommunityPostCommentRespVO> convertAppPage(PageResult<CommunityPostCommentDO> page);

    CommunityPostCommentRespVO convert(CommunityPostCommentDO bean);

    PageResult<CommunityPostCommentRespVO> convertPage(PageResult<CommunityPostCommentDO> page);

}
