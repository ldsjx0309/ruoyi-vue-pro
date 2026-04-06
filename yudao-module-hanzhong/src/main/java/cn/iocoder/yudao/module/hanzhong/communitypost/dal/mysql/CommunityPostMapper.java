package cn.iocoder.yudao.module.hanzhong.communitypost.dal.mysql;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.hanzhong.communitypost.controller.admin.vo.CommunityPostPageReqVO;
import cn.iocoder.yudao.module.hanzhong.communitypost.controller.app.vo.AppCommunityPostPageReqVO;
import cn.iocoder.yudao.module.hanzhong.communitypost.dal.dataobject.CommunityPostDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 汉中 社区帖子 Mapper
 *
 * @author hanzhong
 */
@Mapper
public interface CommunityPostMapper extends BaseMapperX<CommunityPostDO> {

    @Update("UPDATE hanzhong_community_post SET view_count = view_count + 1 WHERE id = #{id} AND deleted = 0")
    int incrementViewCount(@Param("id") Long id);

    @Update("UPDATE hanzhong_community_post SET like_count = like_count + 1 WHERE id = #{id} AND deleted = 0")
    int incrementLikeCount(@Param("id") Long id);

    @Update("UPDATE hanzhong_community_post SET like_count = GREATEST(like_count - 1, 0) WHERE id = #{id} AND deleted = 0")
    int decrementLikeCount(@Param("id") Long id);

    @Select("SELECT * FROM hanzhong_community_post WHERE status = 0 AND deleted = 0 ORDER BY view_count DESC LIMIT #{limit}")
    List<CommunityPostDO> selectHotList(@Param("limit") int limit);

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
                .eqIfPresent(CommunityPostDO::getUserId, reqVO.getUserId())
                .eqIfPresent(CommunityPostDO::getCategory, reqVO.getCategory())
                .likeIfPresent(CommunityPostDO::getTitle, reqVO.getKeyword())
                .orderByDesc(CommunityPostDO::getCreateTime));
    }

    default PageResult<CommunityPostDO> selectPageByUserId(AppCommunityPostPageReqVO reqVO, Long userId) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CommunityPostDO>()
                .eq(CommunityPostDO::getUserId, userId)
                .eqIfPresent(CommunityPostDO::getCategory, reqVO.getCategory())
                .likeIfPresent(CommunityPostDO::getTitle, reqVO.getKeyword())
                .orderByDesc(CommunityPostDO::getCreateTime));
    }

}
