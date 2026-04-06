package cn.iocoder.yudao.module.hanzhong.jobcollect.controller.app;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.hanzhong.jobcollect.controller.app.vo.AppJobCollectRespVO;
import cn.iocoder.yudao.module.hanzhong.jobcollect.dal.dataobject.JobCollectDO;
import cn.iocoder.yudao.module.hanzhong.jobcollect.service.JobCollectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 用户 APP - 汉中 职位收藏
 *
 * @author hanzhong
 */
@RestController
@RequestMapping("/hanzhong/app/job-collect")
@Tag(name = "用户 APP - 汉中 职位收藏")
@Validated
public class AppJobCollectController {

    @Resource
    private JobCollectService jobCollectService;

    @PostMapping("/toggle")
    @Operation(summary = "切换职位收藏状态（收藏/取消收藏）")
    @Parameter(name = "jobId", description = "职位编号", required = true, example = "1024")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Boolean> toggleCollect(@RequestParam("jobId") Long jobId) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        return success(jobCollectService.toggleCollect(userId, jobId));
    }

    @GetMapping("/is-collected")
    @Operation(summary = "判断是否已收藏该职位")
    @Parameter(name = "jobId", description = "职位编号", required = true, example = "1024")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Boolean> isCollected(@RequestParam("jobId") Long jobId) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        return success(jobCollectService.isCollected(userId, jobId));
    }

    @GetMapping("/my-page")
    @Operation(summary = "获取我的收藏职位分页")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<PageResult<AppJobCollectRespVO>> getMyCollectPage(@Valid PageParam pageParam) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        PageResult<JobCollectDO> pageResult = jobCollectService.getMyCollectPage(pageParam, userId);
        List<AppJobCollectRespVO> list = pageResult.getList().stream().map(d -> {
            AppJobCollectRespVO vo = new AppJobCollectRespVO();
            BeanUtils.copyProperties(d, vo);
            return vo;
        }).collect(Collectors.toList());
        return success(new PageResult<>(list, pageResult.getTotal()));
    }

    @DeleteMapping("/delete")
    @Operation(summary = "取消收藏职位")
    @Parameter(name = "id", description = "收藏记录编号", required = true, example = "1024")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Boolean> deleteCollect(@RequestParam("id") Long id) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        jobCollectService.deleteCollect(id, userId);
        return success(true);
    }

    @GetMapping("/collected-job-ids")
    @Operation(summary = "获取我收藏的职位ID列表（用于列表页批量判断收藏状态）")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<List<Long>> getMyCollectedJobIds() {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        PageResult<JobCollectDO> pageResult = jobCollectService.getMyCollectPage(
                new cn.iocoder.yudao.framework.common.pojo.PageParam() {{ setPageNo(1); setPageSize(200); }},
                userId);
        List<Long> jobIds = pageResult.getList().stream()
                .map(JobCollectDO::getJobId)
                .collect(Collectors.toList());
        return success(jobIds);
    }

}
