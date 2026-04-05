package cn.iocoder.yudao.module.hanzhong.banner.convert;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.banner.controller.admin.vo.BannerCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.banner.controller.admin.vo.BannerRespVO;
import cn.iocoder.yudao.module.hanzhong.banner.controller.admin.vo.BannerUpdateReqVO;
import cn.iocoder.yudao.module.hanzhong.banner.controller.app.vo.AppBannerRespVO;
import cn.iocoder.yudao.module.hanzhong.banner.dal.dataobject.BannerDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 汉中 Banner Convert
 *
 * @author hanzhong
 */
@Mapper
public interface BannerConvert {

    BannerConvert INSTANCE = Mappers.getMapper(BannerConvert.class);

    BannerDO convert(BannerCreateReqVO createReqVO);

    BannerDO convert(BannerUpdateReqVO updateReqVO);

    BannerRespVO convert(BannerDO banner);

    PageResult<BannerRespVO> convertPage(PageResult<BannerDO> pageResult);

    List<AppBannerRespVO> convertAppList(List<BannerDO> list);

}
