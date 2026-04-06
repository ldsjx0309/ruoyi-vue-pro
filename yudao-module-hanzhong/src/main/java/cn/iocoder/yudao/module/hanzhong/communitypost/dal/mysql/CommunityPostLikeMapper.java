package cn.iocoder.yudao.module.hanzhong.communitypost.dal.mysql;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.hanzhong.communitypost.dal.dataobject.CommunityPostLikeDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 汉中 社区帖子点赞 Mapper
 *
 * @author hanzhong
 */
@Mapper
public interface CommunityPostLikeMapper extends BaseMapperX<CommunityPostLikeDO> {

    default CommunityPostLikeDO selectByUserIdAndPostId(Long userId, Long postId) {
        return selectOne(new LambdaQueryWrapper<CommunityPostLikeDO>()
                .eq(CommunityPostLikeDO::getUserId, userId)
                .eq(CommunityPostLikeDO::getPostId, postId)
                .last("LIMIT 1"));
    }

    /**
     * 物理删除点赞记录，避免软删除后唯一键冲突
     */
    @Delete("DELETE FROM hanzhong_community_post_like WHERE user_id = #{userId} AND post_id = #{postId}")
    int deleteByUserIdAndPostId(@Param("userId") Long userId, @Param("postId") Long postId);

}
