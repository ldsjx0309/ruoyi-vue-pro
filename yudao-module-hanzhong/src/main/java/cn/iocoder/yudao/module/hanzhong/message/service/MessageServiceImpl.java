package cn.iocoder.yudao.module.hanzhong.message.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.message.controller.admin.vo.MessageCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.message.controller.admin.vo.MessagePageReqVO;
import cn.iocoder.yudao.module.hanzhong.message.controller.admin.vo.MessageUpdateReqVO;
import cn.iocoder.yudao.module.hanzhong.message.controller.app.vo.AppMessagePageReqVO;
import cn.iocoder.yudao.module.hanzhong.message.convert.MessageConvert;
import cn.iocoder.yudao.module.hanzhong.message.dal.dataobject.MessageDO;
import cn.iocoder.yudao.module.hanzhong.message.dal.mysql.MessageMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.hanzhong.enums.ErrorCodeConstants.MESSAGE_NOT_EXISTS;

/**
 * 汉中 消息 Service 实现类
 *
 * @author hanzhong
 */
@Service
@Validated
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageMapper messageMapper;

    @Override
    public Long createMessage(MessageCreateReqVO createReqVO) {
        MessageDO message = MessageConvert.INSTANCE.convert(createReqVO);
        message.setIsRead(Boolean.FALSE);
        messageMapper.insert(message);
        return message.getId();
    }

    @Override
    public void updateMessage(MessageUpdateReqVO updateReqVO) {
        validateMessageExists(updateReqVO.getId());
        MessageDO updateObj = MessageConvert.INSTANCE.convert(updateReqVO);
        messageMapper.updateById(updateObj);
    }

    @Override
    public void deleteMessage(Long id) {
        validateMessageExists(id);
        messageMapper.deleteById(id);
    }

    private void validateMessageExists(Long id) {
        if (messageMapper.selectById(id) == null) {
            throw exception(MESSAGE_NOT_EXISTS);
        }
    }

    @Override
    public MessageDO getMessage(Long id) {
        return messageMapper.selectById(id);
    }

    @Override
    public PageResult<MessageDO> getMessagePage(MessagePageReqVO pageReqVO) {
        return messageMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<MessageDO> getMessagePageByUserId(AppMessagePageReqVO pageReqVO, Long userId) {
        return messageMapper.selectPageByUserId(pageReqVO, userId);
    }

    @Override
    public void readMessage(Long id, Long userId) {
        MessageDO message = messageMapper.selectById(id);
        if (message == null || !message.getUserId().equals(userId)) {
            throw exception(MESSAGE_NOT_EXISTS);
        }
        MessageDO updateObj = new MessageDO();
        updateObj.setId(id);
        updateObj.setIsRead(Boolean.TRUE);
        messageMapper.updateById(updateObj);
    }

    @Override
    public Long getUnreadMessageCount(Long userId) {
        return messageMapper.selectUnreadCountByUserId(userId);
    }

}
