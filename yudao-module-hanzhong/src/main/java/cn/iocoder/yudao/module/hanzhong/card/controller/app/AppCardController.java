package cn.iocoder.yudao.module.hanzhong.card.controller.app;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.hanzhong.card.controller.app.vo.AppCardRespVO;
import cn.iocoder.yudao.module.hanzhong.card.controller.app.vo.AppCardSaveReqVO;
import cn.iocoder.yudao.module.hanzhong.card.convert.CardConvert;
import cn.iocoder.yudao.module.hanzhong.card.dal.dataobject.CardDO;
import cn.iocoder.yudao.module.hanzhong.card.service.CardService;
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
 * 用户 APP - 汉中 名片
 *
 * @author hanzhong
 */
@RestController
@RequestMapping("/hanzhong/app/card")
@Tag(name = "用户 APP - 汉中 名片")
@Validated
public class AppCardController {

    @Resource
    private CardService cardService;

    @GetMapping("/get")
    @Operation(summary = "获取我的名片")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<AppCardRespVO> getMyCard() {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        CardDO card = cardService.getMyCard(userId);
        return success(CardConvert.INSTANCE.convertApp(card));
    }

    @PostMapping("/create-or-update")
    @Operation(summary = "创建或更新我的名片")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Boolean> createOrUpdateMyCard(@Valid @RequestBody AppCardSaveReqVO saveReqVO) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        cardService.createOrUpdateMyCard(userId, saveReqVO);
        return success(true);
    }

    @GetMapping("/get-by-id")
    @Operation(summary = "根据编号获取名片（查看他人名片）")
    @Parameter(name = "id", description = "名片编号", required = true, example = "1024")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<AppCardRespVO> getCardById(@RequestParam("id") Long id) {
        CardDO card = cardService.getCard(id);
        return success(CardConvert.INSTANCE.convertApp(card));
    }

}
