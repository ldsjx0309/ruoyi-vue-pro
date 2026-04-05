package cn.iocoder.yudao.module.hanzhong.banner.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.banner.controller.admin.vo.BannerCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.banner.controller.admin.vo.BannerPageReqVO;
import cn.iocoder.yudao.module.hanzhong.banner.controller.admin.vo.BannerUpdateReqVO;
import cn.iocoder.yudao.module.hanzhong.banner.dal.dataobject.BannerDO;

import java.util.List;

/**
 * 汉中 Banner Service 接口
 *
 * @author hanzhong
 */
public interface BannerService {

    /**
     * 创建 Banner
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBanner(BannerCreateReqVO createReqVO);

    /**
     * 更新 Banner
     *
     * @param updateReqVO 更新信息
     */
    void updateBanner(BannerUpdateReqVO updateReqVO);

    /**
     * 更新 Banner 状态
     *
     * @param id     Banner 编号
     * @param status 状态
     */
    void updateBannerStatus(Long id, Integer status);

    /**
     * 删除 Banner
     *
     * @param id 编号
     */
    void deleteBanner(Long id);

    /**
     * 获得 Banner
     *
     * @param id 编号
     * @return Banner
     */
    BannerDO getBanner(Long id);

    /**
     * 获得 Banner 分页
     *
     * @param pageReqVO 分页查询
     * @return Banner 分页
     */
    PageResult<BannerDO> getBannerPage(BannerPageReqVO pageReqVO);

    /**
     * 获得当前有效的 Banner 列表（App 使用）
     *
     * @return 有效 Banner 列表
     */
    List<BannerDO> getActiveBannerList();

}
