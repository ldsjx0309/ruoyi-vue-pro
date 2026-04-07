package cn.iocoder.yudao.module.hanzhong.cardexchange.dal.mysql;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.hanzhong.cardexchange.controller.admin.vo.CardExchangePageReqVO;
import cn.iocoder.yudao.module.hanzhong.cardexchange.controller.app.vo.AppCardExchangePageReqVO;
import cn.iocoder.yudao.module.hanzhong.cardexchange.dal.dataobject.CardExchangeDO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 汉中 名片交换 Mapper
 *
 * @author hanzhong
 */
@Mapper
public interface CardExchangeMapper extends BaseMapperX<CardExchangeDO> {

    default PageResult<CardExchangeDO> selectPage(CardExchangePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CardExchangeDO>()
                .eqIfPresent(CardExchangeDO::getUserId, reqVO.getUserId())
                .eqIfPresent(CardExchangeDO::getTargetUserId, reqVO.getTargetUserId())
                .betweenIfPresent(CardExchangeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CardExchangeDO::getCreateTime));
    }

    default PageResult<CardExchangeDO> selectPageByUserId(AppCardExchangePageReqVO reqVO, Long userId) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CardExchangeDO>()
                .eq(CardExchangeDO::getUserId, userId)
                .orderByDesc(CardExchangeDO::getCreateTime));
    }

    default Long selectCountByUserIdAndExchangeTimeBetween(Long userId, LocalDateTime start, LocalDateTime end) {
        return selectCount(new LambdaQueryWrapperX<CardExchangeDO>()
                .eq(CardExchangeDO::getUserId, userId)
                .betweenIfPresent(CardExchangeDO::getExchangeTime, new LocalDateTime[]{start, end}));
    }

    default List<CardExchangeDO> selectRecentListByUserId(Long userId, int limit) {
        int safeLimit = Math.max(1, Math.min(limit, 20));
        PageParam pageParam = new PageParam();
        pageParam.setPageNo(1);
        pageParam.setPageSize(safeLimit);
        return selectPage(pageParam, new LambdaQueryWrapperX<CardExchangeDO>()
                .eq(CardExchangeDO::getUserId, userId)
                .orderByDesc(CardExchangeDO::getExchangeTime)).getList();
    }

}
