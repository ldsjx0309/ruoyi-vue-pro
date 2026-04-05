package cn.iocoder.yudao.module.hanzhong.card.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.card.controller.admin.vo.CardPageReqVO;
import cn.iocoder.yudao.module.hanzhong.card.controller.app.vo.AppCardSaveReqVO;
import cn.iocoder.yudao.module.hanzhong.card.convert.CardConvert;
import cn.iocoder.yudao.module.hanzhong.card.dal.dataobject.CardDO;
import cn.iocoder.yudao.module.hanzhong.card.dal.mysql.CardMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

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

    private void validateCardExists(Long id) {
        if (cardMapper.selectById(id) == null) {
            throw exception(CARD_NOT_EXISTS);
        }
    }

}
