package cn.iocoder.yudao.module.hanzhong.userprofile.controller.admin;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.userprofile.controller.admin.vo.UserProfilePageReqVO;
import cn.iocoder.yudao.module.hanzhong.userprofile.controller.admin.vo.UserProfileRespVO;
import cn.iocoder.yudao.module.hanzhong.userprofile.controller.admin.vo.UserProfileUpdateStatusReqVO;
import cn.iocoder.yudao.module.hanzhong.userprofile.convert.UserProfileConvert;
import cn.iocoder.yudao.module.hanzhong.userprofile.dal.dataobject.UserProfileDO;
import cn.iocoder.yudao.module.hanzhong.userprofile.service.UserProfileService;
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
 * 管理后台 - 汉中 用户档案管理
 *
 * @author hanzhong
 */
@Tag(name = "管理后台 - 汉中 用户档案管理")
@RestController
@RequestMapping("/hanzhong/user-profile")
@Validated
public class UserProfileController {

    @Resource
    private UserProfileService userProfileService;

    @PutMapping("/update-status")
    @Operation(summary = "更新用户档案状态")
    @PreAuthorize("@ss.hasPermission('hanzhong:user-profile:update')")
    public CommonResult<Boolean> updateUserProfileStatus(@Valid @RequestBody UserProfileUpdateStatusReqVO updateReqVO) {
        userProfileService.updateUserProfileStatus(updateReqVO.getId(), updateReqVO.getStatus());
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得用户档案详情")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hanzhong:user-profile:query')")
    public CommonResult<UserProfileRespVO> getUserProfile(@RequestParam("id") Long id) {
        UserProfileDO profile = userProfileService.getUserProfile(id);
        return success(UserProfileConvert.INSTANCE.convert(profile));
    }

    @GetMapping("/page")
    @Operation(summary = "获得用户档案分页")
    @PreAuthorize("@ss.hasPermission('hanzhong:user-profile:query')")
    public CommonResult<PageResult<UserProfileRespVO>> getUserProfilePage(@Valid UserProfilePageReqVO pageVO) {
        PageResult<UserProfileDO> pageResult = userProfileService.getUserProfilePage(pageVO);
        return success(UserProfileConvert.INSTANCE.convertPage(pageResult));
    }

}
