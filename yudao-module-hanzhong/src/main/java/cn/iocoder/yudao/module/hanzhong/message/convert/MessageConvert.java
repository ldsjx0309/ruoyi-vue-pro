package cn.iocoder.yudao.module.hanzhong.message.convert;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.message.controller.admin.vo.MessageCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.message.controller.admin.vo.MessageRespVO;
import cn.iocoder.yudao.module.hanzhong.message.controller.admin.vo.MessageUpdateReqVO;
import cn.iocoder.yudao.module.hanzhong.message.controller.app.vo.AppMessageRespVO;
import cn.iocoder.yudao.module.hanzhong.message.dal.dataobject.MessageDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 汉中 消息 Convert
 *
 * @author hanzhong
 */
@Mapper
public interface MessageConvert {

    MessageConvert INSTANCE = Mappers.getMapper(MessageConvert.class);

    MessageDO convert(MessageCreateReqVO createReqVO);

    MessageDO convert(MessageUpdateReqVO updateReqVO);

    MessageRespVO convert(MessageDO message);

    PageResult<MessageRespVO> convertPage(PageResult<MessageDO> pageResult);

    AppMessageRespVO convertApp(MessageDO message);

    PageResult<AppMessageRespVO> convertAppPage(PageResult<MessageDO> pageResult);

}
