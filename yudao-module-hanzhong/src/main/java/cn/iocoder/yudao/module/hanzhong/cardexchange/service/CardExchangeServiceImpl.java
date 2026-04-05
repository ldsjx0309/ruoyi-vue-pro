package cn.iocoder.yudao.module.hanzhong.cardexchange.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.card.dal.dataobject.CardDO;
import cn.iocoder.yudao.module.hanzhong.card.dal.mysql.CardMapper;
import cn.iocoder.yudao.module.hanzhong.cardexchange.controller.admin.vo.CardExchangePageReqVO;
import cn.iocoder.yudao.module.hanzhong.cardexchange.controller.app.vo.AppCardExchangeCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.cardexchange.controller.app.vo.AppCardExchangePageReqVO;
import cn.iocoder.yudao.module.hanzhong.cardexchange.dal.dataobject.CardExchangeDO;
import cn.iocoder.yudao.module.hanzhong.cardexchange.dal.mysql.CardExchangeMapper;
import cn.iocoder.yudao.module.hanzhong.message.service.MessageService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.hanzhong.enums.ErrorCodeConstants.CARD_EXCHANGE_SELF_NOT_ALLOWED;
import static cn.iocoder.yudao.module.hanzhong.enums.ErrorCodeConstants.CARD_NOT_EXISTS;

/**
 * 汉中 名片交换 Service 实现类
 *
 * @author hanzhong
 */
@Service
@Validated
public class CardExchangeServiceImpl implements CardExchangeService {

    @Resource
    private CardExchangeMapper cardExchangeMapper;

    @Resource
    private CardMapper cardMapper;

    @Resource
    @Lazy
    private MessageService messageService;

    @Override
    public Long createCardExchange(Long userId, AppCardExchangeCreateReqVO createReqVO) {
        // 校验不能与自己交换
        if (userId.equals(createReqVO.getTargetUserId())) {
            throw exception(CARD_EXCHANGE_SELF_NOT_ALLOWED);
        }
        CardDO card = cardMapper.selectById(createReqVO.getTargetCardId());
        if (card == null) {
            throw exception(CARD_NOT_EXISTS);
        }
        CardExchangeDO exchange = new CardExchangeDO();
        exchange.setUserId(userId);
        exchange.setTargetUserId(createReqVO.getTargetUserId());
        exchange.setTargetCardId(createReqVO.getTargetCardId());
        exchange.setTargetName(card.getName());
        exchange.setTargetCompany(card.getCompany());
        exchange.setExchangeTime(LocalDateTime.now());
        cardExchangeMapper.insert(exchange);
        // 通知被查看名片的用户
        messageService.sendSystemMessage(createReqVO.getTargetUserId(), "您的名片被查看",
                "有人查看了您的名片，快去了解一下吧！");
        return exchange.getId();
    }

    @Override
    public CardExchangeDO getCardExchange(Long id) {
        return cardExchangeMapper.selectById(id);
    }

    @Override
    public PageResult<CardExchangeDO> getCardExchangePage(CardExchangePageReqVO pageReqVO) {
        return cardExchangeMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<CardExchangeDO> getMyCardExchangePage(AppCardExchangePageReqVO pageReqVO, Long userId) {
        return cardExchangeMapper.selectPageByUserId(pageReqVO, userId);
    }

}
