package cn.iocoder.yudao.module.hanzhong.message.controller.admin;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.message.controller.admin.vo.*;
import cn.iocoder.yudao.module.hanzhong.message.convert.MessageConvert;
import cn.iocoder.yudao.module.hanzhong.message.dal.dataobject.MessageDO;
import cn.iocoder.yudao.module.hanzhong.message.service.MessageService;
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
 * 管理后台 - 汉中 消息管理
 *
 * @author hanzhong
 */
@Tag(name = "管理后台 - 汉中 消息管理")
@RestController
@RequestMapping("/hanzhong/message")
@Validated
public class MessageController {

    @Resource
    private MessageService messageService;

    @PostMapping("/create")
    @Operation(summary = "创建消息")
    @PreAuthorize("@ss.hasPermission('hanzhong:message:create')")
    public CommonResult<Long> createMessage(@Valid @RequestBody MessageCreateReqVO createReqVO) {
        return success(messageService.createMessage(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新消息")
    @PreAuthorize("@ss.hasPermission('hanzhong:message:update')")
    public CommonResult<Boolean> updateMessage(@Valid @RequestBody MessageUpdateReqVO updateReqVO) {
        messageService.updateMessage(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除消息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('hanzhong:message:delete')")
    public CommonResult<Boolean> deleteMessage(@RequestParam("id") Long id) {
        messageService.deleteMessage(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得消息详情")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hanzhong:message:query')")
    public CommonResult<MessageRespVO> getMessage(@RequestParam("id") Long id) {
        MessageDO message = messageService.getMessage(id);
        return success(MessageConvert.INSTANCE.convert(message));
    }

    @GetMapping("/page")
    @Operation(summary = "获得消息分页")
    @PreAuthorize("@ss.hasPermission('hanzhong:message:query')")
    public CommonResult<PageResult<MessageRespVO>> getMessagePage(@Valid MessagePageReqVO pageVO) {
        PageResult<MessageDO> pageResult = messageService.getMessagePage(pageVO);
        return success(MessageConvert.INSTANCE.convertPage(pageResult));
    }

}
