package cn.iocoder.yudao.module.hanzhong.banner.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.banner.controller.admin.vo.BannerCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.banner.controller.admin.vo.BannerPageReqVO;
import cn.iocoder.yudao.module.hanzhong.banner.controller.admin.vo.BannerUpdateReqVO;
import cn.iocoder.yudao.module.hanzhong.banner.convert.BannerConvert;
import cn.iocoder.yudao.module.hanzhong.banner.dal.dataobject.BannerDO;
import cn.iocoder.yudao.module.hanzhong.banner.dal.mysql.BannerMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.hanzhong.enums.ErrorCodeConstants.BANNER_NOT_EXISTS;

/**
 * 汉中 Banner Service 实现类
 *
 * @author hanzhong
 */
@Service
@Validated
public class BannerServiceImpl implements BannerService {

    @Resource
    private BannerMapper bannerMapper;

    @Override
    public Long createBanner(BannerCreateReqVO createReqVO) {
        BannerDO banner = BannerConvert.INSTANCE.convert(createReqVO);
        bannerMapper.insert(banner);
        return banner.getId();
    }

    @Override
    public void updateBanner(BannerUpdateReqVO updateReqVO) {
        validateBannerExists(updateReqVO.getId());
        BannerDO updateObj = BannerConvert.INSTANCE.convert(updateReqVO);
        bannerMapper.updateById(updateObj);
    }

    @Override
    public void updateBannerStatus(Long id, Integer status) {
        validateBannerExists(id);
        BannerDO updateObj = new BannerDO();
        updateObj.setId(id);
        updateObj.setStatus(status);
        bannerMapper.updateById(updateObj);
    }

    @Override
    public void deleteBanner(Long id) {
        validateBannerExists(id);
        bannerMapper.deleteById(id);
    }

    private void validateBannerExists(Long id) {
        if (bannerMapper.selectById(id) == null) {
            throw exception(BANNER_NOT_EXISTS);
        }
    }

    @Override
    public BannerDO getBanner(Long id) {
        return bannerMapper.selectById(id);
    }

    @Override
    public PageResult<BannerDO> getBannerPage(BannerPageReqVO pageReqVO) {
        return bannerMapper.selectPage(pageReqVO);
    }

    @Override
    public List<BannerDO> getActiveBannerList() {
        return bannerMapper.selectListByActive();
    }

}
