package cn.iocoder.yudao.module.hanzhong.message.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.message.controller.admin.vo.MessageCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.message.controller.admin.vo.MessagePageReqVO;
import cn.iocoder.yudao.module.hanzhong.message.controller.admin.vo.MessageUpdateReqVO;
import cn.iocoder.yudao.module.hanzhong.message.controller.app.vo.AppMessagePageReqVO;
import cn.iocoder.yudao.module.hanzhong.message.dal.dataobject.MessageDO;

/**
 * 汉中 消息 Service 接口
 *
 * @author hanzhong
 */
public interface MessageService {

    /**
     * 创建消息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMessage(MessageCreateReqVO createReqVO);

    /**
     * 更新消息
     *
     * @param updateReqVO 更新信息
     */
    void updateMessage(MessageUpdateReqVO updateReqVO);

    /**
     * 删除消息
     *
     * @param id 编号
     */
    void deleteMessage(Long id);

    /**
     * 获得消息
     *
     * @param id 编号
     * @return 消息
     */
    MessageDO getMessage(Long id);

    /**
     * 获得消息分页
     *
     * @param pageReqVO 分页查询
     * @return 消息分页
     */
    PageResult<MessageDO> getMessagePage(MessagePageReqVO pageReqVO);

    /**
     * 获得用户消息分页（App 使用）
     *
     * @param pageReqVO 分页查询
     * @param userId    用户编号
     * @return 消息分页
     */
    PageResult<MessageDO> getMessagePageByUserId(AppMessagePageReqVO pageReqVO, Long userId);

    /**
     * 标记消息为已读
     *
     * @param id     消息编号
     * @param userId 用户编号（用于校验权限）
     */
    void readMessage(Long id, Long userId);

    /**
     * 获得用户未读消息数量
     *
     * @param userId 用户编号
     * @return 未读消息数量
     */
    Long getUnreadMessageCount(Long userId);

    /**
     * 标记所有消息为已读
     *
     * @param userId 用户编号
     */
    void readAllMessages(Long userId);

}
