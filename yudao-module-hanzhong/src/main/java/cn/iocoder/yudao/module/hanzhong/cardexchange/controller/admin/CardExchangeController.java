package cn.iocoder.yudao.module.hanzhong.cardexchange.controller.admin;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.cardexchange.controller.admin.vo.CardExchangePageReqVO;
import cn.iocoder.yudao.module.hanzhong.cardexchange.controller.admin.vo.CardExchangeRespVO;
import cn.iocoder.yudao.module.hanzhong.cardexchange.convert.CardExchangeConvert;
import cn.iocoder.yudao.module.hanzhong.cardexchange.dal.dataobject.CardExchangeDO;
import cn.iocoder.yudao.module.hanzhong.cardexchange.service.CardExchangeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 管理后台 - 汉中 名片交换管理
 *
 * @author hanzhong
 */
@Tag(name = "管理后台 - 汉中 名片交换管理")
@RestController
@RequestMapping("/hanzhong/card-exchange")
@Validated
public class CardExchangeController {

    @Resource
    private CardExchangeService cardExchangeService;

    @GetMapping("/get")
    @Operation(summary = "获得名片交换记录详情")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hanzhong:card-exchange:query')")
    public CommonResult<CardExchangeRespVO> getCardExchange(@RequestParam("id") Long id) {
        CardExchangeDO exchange = cardExchangeService.getCardExchange(id);
        return success(CardExchangeConvert.INSTANCE.convert(exchange));
    }

    @GetMapping("/page")
    @Operation(summary = "获得名片交换分页")
    @PreAuthorize("@ss.hasPermission('hanzhong:card-exchange:query')")
    public CommonResult<PageResult<CardExchangeRespVO>> getCardExchangePage(@Valid CardExchangePageReqVO pageVO) {
        PageResult<CardExchangeDO> pageResult = cardExchangeService.getCardExchangePage(pageVO);
        return success(CardExchangeConvert.INSTANCE.convertPage(pageResult));
    }

}
