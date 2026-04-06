package cn.iocoder.yudao.module.hanzhong.message.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.message.controller.admin.vo.MessageBroadcastReqVO;
import cn.iocoder.yudao.module.hanzhong.message.controller.admin.vo.MessageCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.message.controller.admin.vo.MessagePageReqVO;
import cn.iocoder.yudao.module.hanzhong.message.controller.admin.vo.MessageUpdateReqVO;
import cn.iocoder.yudao.module.hanzhong.message.controller.app.vo.AppMessagePageReqVO;
import cn.iocoder.yudao.module.hanzhong.message.convert.MessageConvert;
import cn.iocoder.yudao.module.hanzhong.message.dal.dataobject.MessageDO;
import cn.iocoder.yudao.module.hanzhong.message.dal.mysql.MessageMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void readAllMessages(Long userId) {
        messageMapper.markAllReadByUserId(userId);
    }

    @Override
    public void sendSystemMessage(Long userId, String title, String content) {
        MessageDO message = new MessageDO();
        message.setUserId(userId);
        message.setTitle(title);
        message.setContent(content);
        message.setType(1); // 1-通知
        message.setIsRead(Boolean.FALSE);
        messageMapper.insert(message);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int broadcastMessage(MessageBroadcastReqVO reqVO) {
        List<Long> userIds = reqVO.getUserIds();
        if (userIds == null || userIds.isEmpty()) {
            return 0;
        }
        int type = reqVO.getType() != null ? reqVO.getType() : 1;
        List<MessageDO> messages = new ArrayList<>(userIds.size());
        for (Long uid : userIds) {
            MessageDO message = new MessageDO();
            message.setUserId(uid);
            message.setTitle(reqVO.getTitle());
            message.setContent(reqVO.getContent());
            message.setType(type);
            message.setIsRead(Boolean.FALSE);
            messages.add(message);
        }
        messageMapper.insertBatch(messages);
        return messages.size();
    }

    @Override
    public void deleteMyMessage(Long id, Long userId) {
        MessageDO message = messageMapper.selectById(id);
        if (message == null || !message.getUserId().equals(userId)) {
            throw exception(MESSAGE_NOT_EXISTS);
        }
        messageMapper.deleteById(id);
    }

}
