package cn.iocoder.yudao.module.hanzhong.userprofile.controller.app;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.hanzhong.userprofile.controller.app.vo.AppUserProfileRespVO;
import cn.iocoder.yudao.module.hanzhong.userprofile.controller.app.vo.AppUserProfileSaveReqVO;
import cn.iocoder.yudao.module.hanzhong.userprofile.convert.UserProfileConvert;
import cn.iocoder.yudao.module.hanzhong.userprofile.dal.dataobject.UserProfileDO;
import cn.iocoder.yudao.module.hanzhong.userprofile.service.UserProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 用户 APP - 汉中 用户档案
 *
 * @author hanzhong
 */
@RestController
@RequestMapping("/hanzhong/app/user-profile")
@Tag(name = "用户 APP - 汉中 用户档案")
@Validated
public class AppUserProfileController {

    @Resource
    private UserProfileService userProfileService;

    @GetMapping("/get")
    @Operation(summary = "获取我的用户档案")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<AppUserProfileRespVO> getMyProfile() {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        UserProfileDO profile = userProfileService.getMyProfile(userId);
        return success(UserProfileConvert.INSTANCE.convertApp(profile));
    }

    @GetMapping("/get-by-user")
    @Operation(summary = "查看指定用户的档案（公开信息）")
    @io.swagger.v3.oas.annotations.Parameter(name = "userId", description = "用户编号", required = true, example = "1024")
    @javax.annotation.security.PermitAll
    public CommonResult<AppUserProfileRespVO> getProfileByUserId(@RequestParam("userId") Long userId) {
        UserProfileDO profile = userProfileService.getMyProfile(userId);
        return success(UserProfileConvert.INSTANCE.convertApp(profile));
    }

    @PutMapping("/create-or-update")
    @Operation(summary = "创建或更新我的用户档案")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Boolean> createOrUpdateMyProfile(@Valid @RequestBody AppUserProfileSaveReqVO saveReqVO) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        userProfileService.createOrUpdateMyProfile(userId, saveReqVO);
        return success(true);
    }

}
