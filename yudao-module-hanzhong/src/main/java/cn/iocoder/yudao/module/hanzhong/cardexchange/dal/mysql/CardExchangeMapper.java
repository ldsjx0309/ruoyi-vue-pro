package cn.iocoder.yudao.module.hanzhong.cardexchange.dal.mysql;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.hanzhong.cardexchange.controller.admin.vo.CardExchangePageReqVO;
import cn.iocoder.yudao.module.hanzhong.cardexchange.controller.app.vo.AppCardExchangePageReqVO;
import cn.iocoder.yudao.module.hanzhong.cardexchange.dal.dataobject.CardExchangeDO;
import org.apache.ibatis.annotations.Mapper;

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

}
