package cn.iocoder.yudao.module.hanzhong.jobapply.controller.app;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.hanzhong.jobapply.controller.app.vo.AppJobApplyCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.jobapply.controller.app.vo.AppJobApplyPageReqVO;
import cn.iocoder.yudao.module.hanzhong.jobapply.controller.app.vo.AppJobApplyRespVO;
import cn.iocoder.yudao.module.hanzhong.jobapply.convert.JobApplyConvert;
import cn.iocoder.yudao.module.hanzhong.jobapply.dal.dataobject.JobApplyDO;
import cn.iocoder.yudao.module.hanzhong.jobapply.service.JobApplyService;
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
 * 用户 APP - 汉中 职位申请
 *
 * @author hanzhong
 */
@RestController
@RequestMapping("/hanzhong/app/job-apply")
@Tag(name = "用户 APP - 汉中 职位申请")
@Validated
public class AppJobApplyController {

    @Resource
    private JobApplyService jobApplyService;

    @GetMapping("/page")
    @Operation(summary = "获取我的职位申请分页")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<PageResult<AppJobApplyRespVO>> getMyJobApplyPage(@Valid AppJobApplyPageReqVO pageReqVO) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        PageResult<JobApplyDO> pageResult = jobApplyService.getMyJobApplyPage(pageReqVO, userId);
        return success(JobApplyConvert.INSTANCE.convertAppPage(pageResult));
    }

    @GetMapping("/get")
    @Operation(summary = "获取职位申请详情")
    @Parameter(name = "id", description = "申请编号", required = true, example = "1024")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<AppJobApplyRespVO> getJobApply(@RequestParam("id") Long id) {
        JobApplyDO apply = jobApplyService.getJobApply(id);
        return success(JobApplyConvert.INSTANCE.convertApp(apply));
    }

    @PostMapping("/create")
    @Operation(summary = "投递职位")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Long> createJobApply(@Valid @RequestBody AppJobApplyCreateReqVO createReqVO) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        return success(jobApplyService.createJobApply(userId, createReqVO));
    }

    @DeleteMapping("/withdraw")
    @Operation(summary = "撤回职位申请（仅限已投递状态）")
    @Parameter(name = "id", description = "申请编号", required = true, example = "1024")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Boolean> withdrawJobApply(@RequestParam("id") Long id) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        jobApplyService.withdrawJobApply(id, userId);
        return success(true);
    }

    @GetMapping("/get-by-job")
    @Operation(summary = "根据职位编号获取我的申请状态（已申请则返回申请详情）")
    @Parameter(name = "jobId", description = "职位编号", required = true, example = "1024")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<AppJobApplyRespVO> getMyApplyByJob(@RequestParam("jobId") Long jobId) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        JobApplyDO apply = jobApplyService.getMyApplyByJobId(userId, jobId);
        return success(JobApplyConvert.INSTANCE.convertApp(apply));
    }

}
