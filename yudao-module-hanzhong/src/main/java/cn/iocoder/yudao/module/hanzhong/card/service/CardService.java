package cn.iocoder.yudao.module.hanzhong.card.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.card.controller.admin.vo.CardPageReqVO;
import cn.iocoder.yudao.module.hanzhong.card.controller.app.vo.AppCardSaveReqVO;
import cn.iocoder.yudao.module.hanzhong.card.dal.dataobject.CardDO;

/**
 * 汉中 名片 Service 接口
 *
 * @author hanzhong
 */
public interface CardService {

    /**
     * 创建或更新我的名片
     */
    CardDO createOrUpdateMyCard(Long userId, AppCardSaveReqVO saveReqVO);

    /**
     * 更新名片状态
     */
    void updateCardStatus(Long id, Integer status);

    /**
     * 获得名片
     */
    CardDO getCard(Long id);

    /**
     * 获得我的名片
     */
    CardDO getMyCard(Long userId);

    /**
     * 获得名片分页
     */
    PageResult<CardDO> getCardPage(CardPageReqVO pageReqVO);

    /**
     * 删除名片（管理员操作）
     *
     * @param id 名片编号
     */
    void deleteCard(Long id);

}
