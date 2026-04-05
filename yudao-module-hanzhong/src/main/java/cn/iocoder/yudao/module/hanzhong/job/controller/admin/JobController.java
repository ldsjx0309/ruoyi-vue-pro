package cn.iocoder.yudao.module.hanzhong.job.controller.admin;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.job.controller.admin.vo.*;
import cn.iocoder.yudao.module.hanzhong.job.convert.JobConvert;
import cn.iocoder.yudao.module.hanzhong.job.dal.dataobject.JobDO;
import cn.iocoder.yudao.module.hanzhong.job.service.JobService;
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
 * 管理后台 - 汉中 职位管理
 *
 * @author hanzhong
 */
@Tag(name = "管理后台 - 汉中 职位管理")
@RestController
@RequestMapping("/hanzhong/job")
@Validated
public class JobController {

    @Resource
    private JobService jobService;

    @PostMapping("/create")
    @Operation(summary = "创建职位")
    @PreAuthorize("@ss.hasPermission('hanzhong:job:create')")
    public CommonResult<Long> createJob(@Valid @RequestBody JobCreateReqVO createReqVO) {
        return success(jobService.createJob(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新职位")
    @PreAuthorize("@ss.hasPermission('hanzhong:job:update')")
    public CommonResult<Boolean> updateJob(@Valid @RequestBody JobUpdateReqVO updateReqVO) {
        jobService.updateJob(updateReqVO);
        return success(true);
    }

    @PutMapping("/update-status")
    @Operation(summary = "更新职位状态")
    @PreAuthorize("@ss.hasPermission('hanzhong:job:update')")
    public CommonResult<Boolean> updateJobStatus(@Valid @RequestBody JobUpdateStatusReqVO updateReqVO) {
        jobService.updateJobStatus(updateReqVO.getId(), updateReqVO.getStatus());
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除职位")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('hanzhong:job:delete')")
    public CommonResult<Boolean> deleteJob(@RequestParam("id") Long id) {
        jobService.deleteJob(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得职位详情")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hanzhong:job:query')")
    public CommonResult<JobRespVO> getJob(@RequestParam("id") Long id) {
        JobDO job = jobService.getJob(id);
        return success(JobConvert.INSTANCE.convert(job));
    }

    @GetMapping("/page")
    @Operation(summary = "获得职位分页")
    @PreAuthorize("@ss.hasPermission('hanzhong:job:query')")
    public CommonResult<PageResult<JobRespVO>> getJobPage(@Valid JobPageReqVO pageVO) {
        PageResult<JobDO> pageResult = jobService.getJobPage(pageVO);
        return success(JobConvert.INSTANCE.convertPage(pageResult));
    }

}
