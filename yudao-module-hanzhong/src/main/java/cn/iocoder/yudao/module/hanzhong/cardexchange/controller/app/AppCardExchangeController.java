package cn.iocoder.yudao.module.hanzhong.cardexchange.controller.app;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.hanzhong.cardexchange.controller.app.vo.AppCardExchangeCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.cardexchange.controller.app.vo.AppCardExchangePageReqVO;
import cn.iocoder.yudao.module.hanzhong.cardexchange.controller.app.vo.AppCardExchangeRespVO;
import cn.iocoder.yudao.module.hanzhong.cardexchange.convert.CardExchangeConvert;
import cn.iocoder.yudao.module.hanzhong.cardexchange.dal.dataobject.CardExchangeDO;
import cn.iocoder.yudao.module.hanzhong.cardexchange.service.CardExchangeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 用户 APP - 汉中 名片交换
 *
 * @author hanzhong
 */
@RestController
@RequestMapping("/hanzhong/app/card-exchange")
@Tag(name = "用户 APP - 汉中 名片交换")
@Validated
public class AppCardExchangeController {

    @Resource
    private CardExchangeService cardExchangeService;

    @GetMapping("/page")
    @Operation(summary = "获取我的名片交换记录分页")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<PageResult<AppCardExchangeRespVO>> getMyCardExchangePage(@Valid AppCardExchangePageReqVO pageReqVO) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        PageResult<CardExchangeDO> pageResult = cardExchangeService.getMyCardExchangePage(pageReqVO, userId);
        return success(CardExchangeConvert.INSTANCE.convertAppPage(pageResult));
    }

    @PostMapping("/create")
    @Operation(summary = "记录名片交换")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Long> createCardExchange(@Valid @RequestBody AppCardExchangeCreateReqVO createReqVO) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        return success(cardExchangeService.createCardExchange(userId, createReqVO));
    }

}
