package cn.iocoder.yudao.module.hanzhong.resume.controller.app;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.hanzhong.resume.controller.app.vo.AppResumeRespVO;
import cn.iocoder.yudao.module.hanzhong.resume.controller.app.vo.AppResumeSaveReqVO;
import cn.iocoder.yudao.module.hanzhong.resume.convert.ResumeConvert;
import cn.iocoder.yudao.module.hanzhong.resume.dal.dataobject.ResumeDO;
import cn.iocoder.yudao.module.hanzhong.resume.service.ResumeService;
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
 * 用户 APP - 汉中 简历
 *
 * @author hanzhong
 */
@RestController
@RequestMapping("/hanzhong/app/resume")
@Tag(name = "用户 APP - 汉中 简历")
@Validated
public class AppResumeController {

    private static final int RESUME_COMPLETENESS_FIELD_COUNT = 9;

    @Resource
    private ResumeService resumeService;

    @GetMapping("/get")
    @Operation(summary = "获取我的简历")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<AppResumeRespVO> getMyResume() {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        ResumeDO resume = resumeService.getMyResume(userId);
        AppResumeRespVO respVO = ResumeConvert.INSTANCE.convertApp(resume);
        if (respVO != null) {
            respVO.setCompletionPercent(calculateCompletionPercent(respVO));
        }
        return success(respVO);
    }

    @PostMapping("/create-or-update")
    @Operation(summary = "创建或更新我的简历")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Boolean> createOrUpdateMyResume(@Valid @RequestBody AppResumeSaveReqVO saveReqVO) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        resumeService.createOrUpdateMyResume(userId, saveReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除我的简历")
    @Parameter(name = "id", description = "简历编号", required = true, example = "1024")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Boolean> deleteMyResume(@RequestParam("id") Long id) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        resumeService.deleteMyResume(id, userId);
        return success(true);
    }

    private Integer calculateCompletionPercent(AppResumeRespVO respVO) {
        int score = 0;
        if (respVO.getName() != null && !respVO.getName().isEmpty()) score++;
        if (respVO.getPhone() != null && !respVO.getPhone().isEmpty()) score++;
        if (respVO.getEducation() != null && !respVO.getEducation().isEmpty()) score++;
        if (respVO.getWorkExperience() != null && !respVO.getWorkExperience().isEmpty()) score++;
        if (respVO.getSkills() != null && !respVO.getSkills().isEmpty()) score++;
        if (respVO.getLanguageSkills() != null && !respVO.getLanguageSkills().isEmpty()) score++;
        if (respVO.getCertificates() != null && !respVO.getCertificates().isEmpty()) score++;
        if (respVO.getSelfIntro() != null && !respVO.getSelfIntro().isEmpty()) score++;
        if (respVO.getAttachmentUrl() != null && !respVO.getAttachmentUrl().isEmpty()) score++;
        return score * 100 / RESUME_COMPLETENESS_FIELD_COUNT;
    }

}
