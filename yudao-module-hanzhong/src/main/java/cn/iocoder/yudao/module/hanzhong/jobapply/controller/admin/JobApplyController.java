package cn.iocoder.yudao.module.hanzhong.jobapply.controller.admin;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.jobapply.controller.admin.vo.JobApplyPageReqVO;
import cn.iocoder.yudao.module.hanzhong.jobapply.controller.admin.vo.JobApplyRespVO;
import cn.iocoder.yudao.module.hanzhong.jobapply.controller.admin.vo.JobApplyUpdateStatusReqVO;
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
 * 管理后台 - 汉中 职位申请管理
 *
 * @author hanzhong
 */
@Tag(name = "管理后台 - 汉中 职位申请管理")
@RestController
@RequestMapping("/hanzhong/job-apply")
@Validated
public class JobApplyController {

    @Resource
    private JobApplyService jobApplyService;

    @PutMapping("/update-status")
    @Operation(summary = "更新职位申请状态（可附带备注）")
    @PreAuthorize("@ss.hasPermission('hanzhong:job-apply:update')")
    public CommonResult<Boolean> updateJobApplyStatus(@Valid @RequestBody JobApplyUpdateStatusReqVO updateReqVO) {
        jobApplyService.updateJobApplyStatus(updateReqVO.getId(), updateReqVO.getStatus(), updateReqVO.getRemark());
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得职位申请详情")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hanzhong:job-apply:query')")
    public CommonResult<JobApplyRespVO> getJobApply(@RequestParam("id") Long id) {
        JobApplyDO apply = jobApplyService.getJobApply(id);
        return success(JobApplyConvert.INSTANCE.convert(apply));
    }

    @GetMapping("/page")
    @Operation(summary = "获得职位申请分页")
    @PreAuthorize("@ss.hasPermission('hanzhong:job-apply:query')")
    public CommonResult<PageResult<JobApplyRespVO>> getJobApplyPage(@Valid JobApplyPageReqVO pageVO) {
        PageResult<JobApplyDO> pageResult = jobApplyService.getJobApplyPage(pageVO);
        return success(JobApplyConvert.INSTANCE.convertPage(pageResult));
    }

}
