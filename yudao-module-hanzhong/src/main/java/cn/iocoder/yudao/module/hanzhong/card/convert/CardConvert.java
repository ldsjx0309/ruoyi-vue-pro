package cn.iocoder.yudao.module.hanzhong.card.convert;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.card.controller.admin.vo.CardRespVO;
import cn.iocoder.yudao.module.hanzhong.card.controller.app.vo.AppCardRespVO;
import cn.iocoder.yudao.module.hanzhong.card.controller.app.vo.AppCardSaveReqVO;
import cn.iocoder.yudao.module.hanzhong.card.dal.dataobject.CardDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 汉中 名片 Convert
 *
 * @author hanzhong
 */
@Mapper
public interface CardConvert {

    CardConvert INSTANCE = Mappers.getMapper(CardConvert.class);

    CardDO convert(AppCardSaveReqVO saveReqVO);

    CardRespVO convert(CardDO card);

    PageResult<CardRespVO> convertPage(PageResult<CardDO> pageResult);

    AppCardRespVO convertApp(CardDO card);

    PageResult<AppCardRespVO> convertAppPage(PageResult<CardDO> pageResult);

}
