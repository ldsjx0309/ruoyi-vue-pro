package cn.iocoder.yudao.module.hanzhong.hotkeyword.convert;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.hotkeyword.controller.admin.vo.HotKeywordRespVO;
import cn.iocoder.yudao.module.hanzhong.hotkeyword.controller.admin.vo.HotKeywordSaveReqVO;
import cn.iocoder.yudao.module.hanzhong.hotkeyword.dal.dataobject.HotKeywordDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * 汉中 热门搜索关键词 Convert
 *
 * @author hanzhong
 */
@Mapper
public interface HotKeywordConvert {

    HotKeywordConvert INSTANCE = Mappers.getMapper(HotKeywordConvert.class);

    HotKeywordDO convert(HotKeywordSaveReqVO reqVO);

    HotKeywordRespVO convert(HotKeywordDO bean);

    PageResult<HotKeywordRespVO> convertPage(PageResult<HotKeywordDO> page);

    void update(HotKeywordSaveReqVO reqVO, @MappingTarget HotKeywordDO bean);

}
