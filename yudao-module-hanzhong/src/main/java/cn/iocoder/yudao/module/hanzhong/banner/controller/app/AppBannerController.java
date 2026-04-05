package cn.iocoder.yudao.module.hanzhong.banner.controller.app;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.hanzhong.banner.controller.app.vo.AppBannerRespVO;
import cn.iocoder.yudao.module.hanzhong.banner.convert.BannerConvert;
import cn.iocoder.yudao.module.hanzhong.banner.dal.dataobject.BannerDO;
import cn.iocoder.yudao.module.hanzhong.banner.service.BannerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 用户 APP - 汉中首页 Banner
 *
 * @author hanzhong
 */
@RestController
@RequestMapping("/hanzhong/app/banner")
@Tag(name = "用户 APP - 汉中首页 Banner")
@Validated
public class AppBannerController {

    @Resource
    private BannerService bannerService;

    @GetMapping("/list")
    @Operation(summary = "获取首页 Banner 列表（仅返回当前有效 Banner）")
    @PermitAll
    public CommonResult<List<AppBannerRespVO>> getBannerList() {
        List<BannerDO> bannerList = bannerService.getActiveBannerList();
        return success(BannerConvert.INSTANCE.convertAppList(bannerList));
    }

}
