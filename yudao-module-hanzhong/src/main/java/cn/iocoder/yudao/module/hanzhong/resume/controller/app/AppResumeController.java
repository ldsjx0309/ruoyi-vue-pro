package cn.iocoder.yudao.module.hanzhong.resume.controller.app;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.hanzhong.resume.controller.app.vo.AppResumeRespVO;
import cn.iocoder.yudao.module.hanzhong.resume.controller.app.vo.AppResumeSaveReqVO;
import cn.iocoder.yudao.module.hanzhong.resume.convert.ResumeConvert;
import cn.iocoder.yudao.module.hanzhong.resume.dal.dataobject.ResumeDO;
import cn.iocoder.yudao.module.hanzhong.resume.service.ResumeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 用户 APP - 汉中 简历
 *
 * @author hanzhong
 */
@RestController
@RequestMapping("/hanzhong/app/resume")
@Tag(name = "用户 APP - 汉中 简历")
@Validated
public class AppResumeController {

    @Resource
    private ResumeService resumeService;

    @GetMapping("/get")
    @Operation(summary = "获取我的简历")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<AppResumeRespVO> getMyResume() {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        ResumeDO resume = resumeService.getMyResume(userId);
        return success(ResumeConvert.INSTANCE.convertApp(resume));
    }

    @PostMapping("/create-or-update")
    @Operation(summary = "创建或更新我的简历")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Boolean> createOrUpdateMyResume(@Valid @RequestBody AppResumeSaveReqVO saveReqVO) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        resumeService.createOrUpdateMyResume(userId, saveReqVO);
        return success(true);
    }

}
