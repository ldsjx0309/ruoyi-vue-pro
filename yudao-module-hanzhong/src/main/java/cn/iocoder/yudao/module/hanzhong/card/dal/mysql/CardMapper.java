package cn.iocoder.yudao.module.hanzhong.card.dal.mysql;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.hanzhong.card.controller.admin.vo.CardPageReqVO;
import cn.iocoder.yudao.module.hanzhong.card.controller.app.vo.AppCardPageReqVO;
import cn.iocoder.yudao.module.hanzhong.card.dal.dataobject.CardDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 汉中 名片 Mapper
 *
 * @author hanzhong
 */
@Mapper
public interface CardMapper extends BaseMapperX<CardDO> {

    int MAX_RECOMMENDED_CARDS_LIMIT = 20;

    default PageResult<CardDO> selectPage(CardPageReqVO reqVO) {
        LambdaQueryWrapperX<CardDO> wrapper = new LambdaQueryWrapperX<CardDO>()
                .eqIfPresent(CardDO::getUserId, reqVO.getUserId())
                .eqIfPresent(CardDO::getGroupName, reqVO.getGroupName())
                .eqIfPresent(CardDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(CardDO::getCreateTime, reqVO.getCreateTime());
        if (reqVO.getKeyword() != null && !reqVO.getKeyword().trim().isEmpty()) {
            wrapper.and(w -> w.like(CardDO::getName, reqVO.getKeyword())
                    .or().like(CardDO::getCompany, reqVO.getKeyword())
                    .or().like(CardDO::getTags, reqVO.getKeyword()));
        }
        wrapper.orderByDesc(CardDO::getCreateTime);
        return selectPage(reqVO, wrapper);
    }

    default CardDO selectByUserId(Long userId) {
        return selectOne(CardDO::getUserId, userId);
    }

    default PageResult<CardDO> selectAppPage(AppCardPageReqVO reqVO) {
        LambdaQueryWrapperX<CardDO> wrapper = new LambdaQueryWrapperX<CardDO>()
                .eq(CardDO::getStatus, 0)
                .eqIfPresent(CardDO::getGroupName, reqVO.getGroupName())
                .eqIfPresent(CardDO::getCountry, reqVO.getCountry());
        if (reqVO.getKeyword() != null && !reqVO.getKeyword().trim().isEmpty()) {
            wrapper.and(w -> w.like(CardDO::getName, reqVO.getKeyword())
                    .or().like(CardDO::getCompany, reqVO.getKeyword())
                    .or().like(CardDO::getTags, reqVO.getKeyword()));
        }
        wrapper.orderByDesc(CardDO::getUpdateTime);
        return selectPage(reqVO, wrapper);
    }

    default List<CardDO> selectRecommendedList(int limit) {
        int safeLimit = Math.max(1, Math.min(limit, MAX_RECOMMENDED_CARDS_LIMIT));
        PageParam pageParam = new PageParam();
        pageParam.setPageNo(1);
        pageParam.setPageSize(safeLimit);
        return selectPage(pageParam, new LambdaQueryWrapperX<CardDO>()
                .eq(CardDO::getStatus, 0)
                .orderByDesc(CardDO::getUpdateTime)).getList();
    }

}
