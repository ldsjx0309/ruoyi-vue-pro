package cn.iocoder.yudao.module.hanzhong.card.controller.app;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.hanzhong.card.controller.app.vo.AppCardPageReqVO;
import cn.iocoder.yudao.module.hanzhong.card.controller.app.vo.AppCardRespVO;
import cn.iocoder.yudao.module.hanzhong.card.controller.app.vo.AppCardSaveReqVO;
import cn.iocoder.yudao.module.hanzhong.card.controller.app.vo.AppCardStatsRespVO;
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
        return success(fillCardExtra(CardConvert.INSTANCE.convertApp(card)));
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
        return success(fillCardExtra(CardConvert.INSTANCE.convertApp(card)));
    }

    @GetMapping("/get-by-user-id")
    @Operation(summary = "根据用户编号获取名片（查看他人名片，用于名片交换）")
    @Parameter(name = "userId", description = "用户编号", required = true, example = "1024")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<AppCardRespVO> getCardByUserId(@RequestParam("userId") Long userId) {
        CardDO card = cardService.getMyCard(userId);
        return success(fillCardExtra(CardConvert.INSTANCE.convertApp(card)));
    }

    @GetMapping("/page")
    @Operation(summary = "名片目录分页（支持姓名/公司/标签搜索与分组筛选）")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<PageResult<AppCardRespVO>> getCardPage(@Valid AppCardPageReqVO pageReqVO) {
        PageResult<CardDO> pageResult = cardService.getAppCardPage(pageReqVO);
        PageResult<AppCardRespVO> result = CardConvert.INSTANCE.convertAppPage(pageResult);
        result.getList().forEach(this::fillCardExtra);
        return success(result);
    }

    @GetMapping("/stats")
    @Operation(summary = "获取名片夹统计（本月交换数、累计交换数、最近交换）")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<AppCardStatsRespVO> getCardStats() {
        return success(cardService.getCardStats(SecurityFrameworkUtils.getLoginUserId()));
    }

    private AppCardRespVO fillCardExtra(AppCardRespVO vo) {
        if (vo != null && vo.getMutualFriendCount() == null) {
            vo.setMutualFriendCount(0);
        }
        return vo;
    }

}
