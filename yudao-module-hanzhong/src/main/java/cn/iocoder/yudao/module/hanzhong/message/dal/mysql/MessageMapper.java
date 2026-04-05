package cn.iocoder.yudao.module.hanzhong.message.dal.mysql;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.hanzhong.message.controller.admin.vo.MessagePageReqVO;
import cn.iocoder.yudao.module.hanzhong.message.controller.app.vo.AppMessagePageReqVO;
import cn.iocoder.yudao.module.hanzhong.message.dal.dataobject.MessageDO;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 汉中 消息 Mapper
 *
 * @author hanzhong
 */
@Mapper
public interface MessageMapper extends BaseMapperX<MessageDO> {

    default PageResult<MessageDO> selectPage(MessagePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MessageDO>()
                .eqIfPresent(MessageDO::getUserId, reqVO.getUserId())
                .eqIfPresent(MessageDO::getType, reqVO.getType())
                .eqIfPresent(MessageDO::getIsRead, reqVO.getIsRead())
                .betweenIfPresent(MessageDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MessageDO::getCreateTime));
    }

    default PageResult<MessageDO> selectPageByUserId(AppMessagePageReqVO reqVO, Long userId) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MessageDO>()
                .eq(MessageDO::getUserId, userId)
                .eqIfPresent(MessageDO::getIsRead, reqVO.getIsRead())
                .orderByDesc(MessageDO::getCreateTime));
    }

    default Long selectUnreadCountByUserId(Long userId) {
        return selectCount(new LambdaQueryWrapperX<MessageDO>()
                .eq(MessageDO::getUserId, userId)
                .eq(MessageDO::getIsRead, Boolean.FALSE));
    }

    default void markAllReadByUserId(Long userId) {
        update(null, new LambdaUpdateWrapper<MessageDO>()
                .eq(MessageDO::getUserId, userId)
                .eq(MessageDO::getIsRead, Boolean.FALSE)
                .set(MessageDO::getIsRead, Boolean.TRUE));
    }

}
