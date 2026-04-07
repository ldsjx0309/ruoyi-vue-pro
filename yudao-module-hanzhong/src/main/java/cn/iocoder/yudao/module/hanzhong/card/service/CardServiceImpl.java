package cn.iocoder.yudao.module.hanzhong.card.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.hanzhong.card.controller.admin.vo.CardPageReqVO;
import cn.iocoder.yudao.module.hanzhong.card.controller.app.vo.AppCardPageReqVO;
import cn.iocoder.yudao.module.hanzhong.card.controller.app.vo.AppCardSaveReqVO;
import cn.iocoder.yudao.module.hanzhong.card.controller.app.vo.AppCardStatsRespVO;
import cn.iocoder.yudao.module.hanzhong.card.convert.CardConvert;
import cn.iocoder.yudao.module.hanzhong.card.dal.dataobject.CardDO;
import cn.iocoder.yudao.module.hanzhong.card.dal.mysql.CardMapper;
import cn.iocoder.yudao.module.hanzhong.cardexchange.controller.app.vo.AppCardExchangeRespVO;
import cn.iocoder.yudao.module.hanzhong.cardexchange.convert.CardExchangeConvert;
import cn.iocoder.yudao.module.hanzhong.cardexchange.dal.dataobject.CardExchangeDO;
import cn.iocoder.yudao.module.hanzhong.cardexchange.dal.mysql.CardExchangeMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.hanzhong.enums.ErrorCodeConstants.CARD_NOT_EXISTS;

/**
 * 汉中 名片 Service 实现类
 *
 * @author hanzhong
 */
@Service
@Validated
public class CardServiceImpl implements CardService {

    @Resource
    private CardMapper cardMapper;

    @Resource
    private CardExchangeMapper cardExchangeMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CardDO createOrUpdateMyCard(Long userId, AppCardSaveReqVO saveReqVO) {
        CardDO existing = cardMapper.selectByUserId(userId);
        if (existing == null) {
            CardDO card = CardConvert.INSTANCE.convert(saveReqVO);
            card.setUserId(userId);
            card.setStatus(0);
            cardMapper.insert(card);
            return card;
        } else {
            CardDO updateObj = CardConvert.INSTANCE.convert(saveReqVO);
            updateObj.setId(existing.getId());
            cardMapper.updateById(updateObj);
            return cardMapper.selectById(existing.getId());
        }
    }

    @Override
    public void updateCardStatus(Long id, Integer status) {
        validateCardExists(id);
        CardDO updateObj = new CardDO();
        updateObj.setId(id);
        updateObj.setStatus(status);
        cardMapper.updateById(updateObj);
    }

    @Override
    public CardDO getCard(Long id) {
        return cardMapper.selectById(id);
    }

    @Override
    public CardDO getMyCard(Long userId) {
        return cardMapper.selectByUserId(userId);
    }

    @Override
    public PageResult<CardDO> getCardPage(CardPageReqVO pageReqVO) {
        return cardMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<CardDO> getAppCardPage(AppCardPageReqVO pageReqVO) {
        return cardMapper.selectAppPage(pageReqVO);
    }

    @Override
    public List<CardDO> getRecommendedCards(int limit) {
        return cardMapper.selectRecommendedList(limit);
    }

    @Override
    public AppCardStatsRespVO getCardStats(Long userId) {
        AppCardStatsRespVO respVO = new AppCardStatsRespVO();
        LocalDateTime monthStart = LocalDate.now().withDayOfMonth(1).atStartOfDay();
        LocalDateTime nextMonthStart = monthStart.plusMonths(1);
        respVO.setMonthExchangeCount(cardExchangeMapper.selectCountByUserIdAndExchangeTimeBetween(userId, monthStart, nextMonthStart));
        respVO.setTotalExchangeCount(cardExchangeMapper.selectCount(
                new LambdaQueryWrapperX<CardExchangeDO>().eq(CardExchangeDO::getUserId, userId)));
        List<AppCardExchangeRespVO> recent = cardExchangeMapper.selectRecentListByUserId(userId, 5).stream()
                .map(CardExchangeConvert.INSTANCE::convertApp)
                .collect(Collectors.toList());
        respVO.setRecentExchanges(recent);
        return respVO;
    }

    @Override
    public void deleteCard(Long id) {
        validateCardExists(id);
        cardMapper.deleteById(id);
    }

    private void validateCardExists(Long id) {
        if (cardMapper.selectById(id) == null) {
            throw exception(CARD_NOT_EXISTS);
        }
    }

}
