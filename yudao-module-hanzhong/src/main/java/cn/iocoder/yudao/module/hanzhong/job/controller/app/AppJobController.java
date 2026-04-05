package cn.iocoder.yudao.module.hanzhong.job.controller.app;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.job.controller.app.vo.AppJobPageReqVO;
import cn.iocoder.yudao.module.hanzhong.job.controller.app.vo.AppJobRespVO;
import cn.iocoder.yudao.module.hanzhong.job.convert.JobConvert;
import cn.iocoder.yudao.module.hanzhong.job.dal.dataobject.JobDO;
import cn.iocoder.yudao.module.hanzhong.job.service.JobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.validation.Valid;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 用户 APP - 汉中 职位
 *
 * @author hanzhong
 */
@RestController
@RequestMapping("/hanzhong/app/job")
@Tag(name = "用户 APP - 汉中 职位")
@Validated
public class AppJobController {

    @Resource
    private JobService jobService;

    @GetMapping("/page")
    @Operation(summary = "获取职位分页列表")
    @PermitAll
    public CommonResult<PageResult<AppJobRespVO>> getJobPageForApp(@Valid AppJobPageReqVO pageReqVO) {
        PageResult<JobDO> pageResult = jobService.getJobPageForApp(pageReqVO);
        return success(JobConvert.INSTANCE.convertAppPage(pageResult));
    }

    @GetMapping("/get")
    @Operation(summary = "获取职位详情")
    @Parameter(name = "id", description = "职位编号", required = true, example = "1024")
    @PermitAll
    public CommonResult<AppJobRespVO> getJob(@RequestParam("id") Long id) {
        JobDO job = jobService.getJob(id);
        return success(JobConvert.INSTANCE.convertApp(job));
    }

}
