package cn.iocoder.yudao.module.hanzhong.message.controller.app;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.hanzhong.message.controller.app.vo.AppMessagePageReqVO;
import cn.iocoder.yudao.module.hanzhong.message.controller.app.vo.AppMessageRespVO;
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
 * 用户 APP - 汉中 消息
 *
 * @author hanzhong
 */
@RestController
@RequestMapping("/hanzhong/app/message")
@Tag(name = "用户 APP - 汉中 消息")
@Validated
public class AppMessageController {

    @Resource
    private MessageService messageService;

    @GetMapping("/page")
    @Operation(summary = "获取我的消息分页列表")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<PageResult<AppMessageRespVO>> getMyMessagePage(@Valid AppMessagePageReqVO pageReqVO) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        PageResult<MessageDO> pageResult = messageService.getMessagePageByUserId(pageReqVO, userId);
        return success(MessageConvert.INSTANCE.convertAppPage(pageResult));
    }

    @PutMapping("/read")
    @Operation(summary = "标记消息为已读")
    @Parameter(name = "id", description = "消息编号", required = true, example = "1024")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Boolean> readMessage(@RequestParam("id") Long id) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        messageService.readMessage(id, userId);
        return success(true);
    }

    @GetMapping("/unread-count")
    @Operation(summary = "获取未读消息数量")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Long> getUnreadMessageCount() {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        return success(messageService.getUnreadMessageCount(userId));
    }

}
