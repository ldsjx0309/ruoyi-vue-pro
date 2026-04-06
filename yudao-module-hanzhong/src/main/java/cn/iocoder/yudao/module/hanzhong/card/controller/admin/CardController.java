package cn.iocoder.yudao.module.hanzhong.card.controller.admin;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.card.controller.admin.vo.CardPageReqVO;
import cn.iocoder.yudao.module.hanzhong.card.controller.admin.vo.CardRespVO;
import cn.iocoder.yudao.module.hanzhong.card.controller.admin.vo.CardUpdateStatusReqVO;
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
 * 管理后台 - 汉中 名片管理
 *
 * @author hanzhong
 */
@Tag(name = "管理后台 - 汉中 名片管理")
@RestController
@RequestMapping("/hanzhong/card")
@Validated
public class CardController {

    @Resource
    private CardService cardService;

    @PutMapping("/update-status")
    @Operation(summary = "更新名片状态")
    @PreAuthorize("@ss.hasPermission('hanzhong:card:update')")
    public CommonResult<Boolean> updateCardStatus(@Valid @RequestBody CardUpdateStatusReqVO updateReqVO) {
        cardService.updateCardStatus(updateReqVO.getId(), updateReqVO.getStatus());
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得名片详情")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hanzhong:card:query')")
    public CommonResult<CardRespVO> getCard(@RequestParam("id") Long id) {
        CardDO card = cardService.getCard(id);
        return success(CardConvert.INSTANCE.convert(card));
    }

    @GetMapping("/page")
    @Operation(summary = "获得名片分页")
    @PreAuthorize("@ss.hasPermission('hanzhong:card:query')")
    public CommonResult<PageResult<CardRespVO>> getCardPage(@Valid CardPageReqVO pageVO) {
        PageResult<CardDO> pageResult = cardService.getCardPage(pageVO);
        return success(CardConvert.INSTANCE.convertPage(pageResult));
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除名片")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hanzhong:card:delete')")
    public CommonResult<Boolean> deleteCard(@RequestParam("id") Long id) {
        cardService.deleteCard(id);
        return success(true);
    }

}
