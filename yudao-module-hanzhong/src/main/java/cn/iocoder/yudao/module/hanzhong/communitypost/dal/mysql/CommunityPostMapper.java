package cn.iocoder.yudao.module.hanzhong.communitypost.dal.mysql;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.hanzhong.communitypost.controller.admin.vo.CommunityPostPageReqVO;
import cn.iocoder.yudao.module.hanzhong.communitypost.controller.app.vo.AppCommunityPostPageReqVO;
import cn.iocoder.yudao.module.hanzhong.communitypost.dal.dataobject.CommunityPostDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 汉中 社区帖子 Mapper
 *
 * @author hanzhong
 */
@Mapper
public interface CommunityPostMapper extends BaseMapperX<CommunityPostDO> {

    @Update("UPDATE hanzhong_community_post SET view_count = view_count + 1 WHERE id = #{id} AND deleted = 0")
    int incrementViewCount(@Param("id") Long id);

    default PageResult<CommunityPostDO> selectPage(CommunityPostPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CommunityPostDO>()
                .eqIfPresent(CommunityPostDO::getUserId, reqVO.getUserId())
                .eqIfPresent(CommunityPostDO::getCategory, reqVO.getCategory())
                .eqIfPresent(CommunityPostDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(CommunityPostDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CommunityPostDO::getCreateTime));
    }

    default PageResult<CommunityPostDO> selectPageForApp(AppCommunityPostPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CommunityPostDO>()
                .eq(CommunityPostDO::getStatus, 0)
                .eqIfPresent(CommunityPostDO::getCategory, reqVO.getCategory())
                .orderByDesc(CommunityPostDO::getCreateTime));
    }

    default PageResult<CommunityPostDO> selectPageByUserId(AppCommunityPostPageReqVO reqVO, Long userId) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CommunityPostDO>()
                .eq(CommunityPostDO::getUserId, userId)
                .eqIfPresent(CommunityPostDO::getCategory, reqVO.getCategory())
                .orderByDesc(CommunityPostDO::getCreateTime));
    }

}
