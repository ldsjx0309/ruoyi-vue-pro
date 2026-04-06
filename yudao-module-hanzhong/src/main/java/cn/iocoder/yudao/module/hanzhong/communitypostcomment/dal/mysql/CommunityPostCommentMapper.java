package cn.iocoder.yudao.module.hanzhong.communitypostcomment.dal.mysql;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.hanzhong.communitypostcomment.controller.admin.vo.CommunityPostCommentPageReqVO;
import cn.iocoder.yudao.module.hanzhong.communitypostcomment.controller.app.vo.AppCommunityPostCommentPageReqVO;
import cn.iocoder.yudao.module.hanzhong.communitypostcomment.dal.dataobject.CommunityPostCommentDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 汉中 社区帖子评论 Mapper
 *
 * @author hanzhong
 */
@Mapper
public interface CommunityPostCommentMapper extends BaseMapperX<CommunityPostCommentDO> {

    default PageResult<CommunityPostCommentDO> selectPageByPostId(AppCommunityPostCommentPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapper<CommunityPostCommentDO>()
                .eq(CommunityPostCommentDO::getPostId, reqVO.getPostId())
                .eq(CommunityPostCommentDO::getStatus, 0)
                .orderByAsc(CommunityPostCommentDO::getCreateTime));
    }

    default PageResult<CommunityPostCommentDO> selectPage(CommunityPostCommentPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CommunityPostCommentDO>()
                .eqIfPresent(CommunityPostCommentDO::getPostId, reqVO.getPostId())
                .eqIfPresent(CommunityPostCommentDO::getUserId, reqVO.getUserId())
                .eqIfPresent(CommunityPostCommentDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(CommunityPostCommentDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CommunityPostCommentDO::getCreateTime));
    }

    default long countByPostId(Long postId) {
        return selectCount(new LambdaQueryWrapper<CommunityPostCommentDO>()
                .eq(CommunityPostCommentDO::getPostId, postId)
                .eq(CommunityPostCommentDO::getStatus, 0));
    }

    default PageResult<CommunityPostCommentDO> selectPageByUserId(PageParam pageParam, Long userId) {
        return selectPage(pageParam, new LambdaQueryWrapper<CommunityPostCommentDO>()
                .eq(CommunityPostCommentDO::getUserId, userId)
                .orderByDesc(CommunityPostCommentDO::getCreateTime));
    }

}
