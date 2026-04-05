package cn.iocoder.yudao.module.hanzhong.cardexchange.convert;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.cardexchange.controller.admin.vo.CardExchangeRespVO;
import cn.iocoder.yudao.module.hanzhong.cardexchange.controller.app.vo.AppCardExchangeRespVO;
import cn.iocoder.yudao.module.hanzhong.cardexchange.dal.dataobject.CardExchangeDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 汉中 名片交换 Convert
 *
 * @author hanzhong
 */
@Mapper
public interface CardExchangeConvert {

    CardExchangeConvert INSTANCE = Mappers.getMapper(CardExchangeConvert.class);

    CardExchangeRespVO convert(CardExchangeDO exchange);

    PageResult<CardExchangeRespVO> convertPage(PageResult<CardExchangeDO> pageResult);

    AppCardExchangeRespVO convertApp(CardExchangeDO exchange);

    PageResult<AppCardExchangeRespVO> convertAppPage(PageResult<CardExchangeDO> pageResult);

}
