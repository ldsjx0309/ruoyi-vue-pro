package cn.iocoder.yudao.module.hanzhong.communitypost.dal.mysql;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.hanzhong.communitypost.dal.dataobject.CommunityPostLikeDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.ibatis.annotations.Mapper;

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
                .eq(CommunityPostLikeDO::getPostId, postId));
    }

}
