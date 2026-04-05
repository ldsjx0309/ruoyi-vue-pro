package cn.iocoder.yudao.module.hanzhong.cardexchange.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.cardexchange.controller.admin.vo.CardExchangePageReqVO;
import cn.iocoder.yudao.module.hanzhong.cardexchange.controller.app.vo.AppCardExchangeCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.cardexchange.controller.app.vo.AppCardExchangePageReqVO;
import cn.iocoder.yudao.module.hanzhong.cardexchange.dal.dataobject.CardExchangeDO;

/**
 * 汉中 名片交换 Service 接口
 *
 * @author hanzhong
 */
public interface CardExchangeService {

    /**
     * 记录名片交换
     */
    Long createCardExchange(Long userId, AppCardExchangeCreateReqVO createReqVO);

    /**
     * 获得名片交换记录
     */
    CardExchangeDO getCardExchange(Long id);

    /**
     * 获得名片交换分页（管理员）
     */
    PageResult<CardExchangeDO> getCardExchangePage(CardExchangePageReqVO pageReqVO);

    /**
     * 获得我的名片交换分页
     */
    PageResult<CardExchangeDO> getMyCardExchangePage(AppCardExchangePageReqVO pageReqVO, Long userId);

}
