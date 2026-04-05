package cn.iocoder.yudao.module.hanzhong.card.dal.mysql;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.hanzhong.card.controller.admin.vo.CardPageReqVO;
import cn.iocoder.yudao.module.hanzhong.card.dal.dataobject.CardDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 汉中 名片 Mapper
 *
 * @author hanzhong
 */
@Mapper
public interface CardMapper extends BaseMapperX<CardDO> {

    default PageResult<CardDO> selectPage(CardPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CardDO>()
                .eqIfPresent(CardDO::getUserId, reqVO.getUserId())
                .eqIfPresent(CardDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(CardDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CardDO::getCreateTime));
    }

    default CardDO selectByUserId(Long userId) {
        return selectOne(CardDO::getUserId, userId);
    }

}
