package cn.iocoder.yudao.module.hanzhong.jobcollect.controller.admin;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.jobcollect.controller.admin.vo.JobCollectPageReqVO;
import cn.iocoder.yudao.module.hanzhong.jobcollect.controller.admin.vo.JobCollectRespVO;
import cn.iocoder.yudao.module.hanzhong.jobcollect.dal.dataobject.JobCollectDO;
import cn.iocoder.yudao.module.hanzhong.jobcollect.dal.mysql.JobCollectMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
 * 管理后台 - 汉中 职位收藏管理
 *
 * @author hanzhong
 */
@Tag(name = "管理后台 - 汉中 职位收藏管理")
@RestController
@RequestMapping("/hanzhong/job-collect")
@Validated
public class JobCollectController {

    @Resource
    private JobCollectMapper jobCollectMapper;

    @GetMapping("/page")
    @Operation(summary = "获取职位收藏分页")
    @PreAuthorize("@ss.hasPermission('hanzhong:job-collect:query')")
    public CommonResult<PageResult<JobCollectRespVO>> getJobCollectPage(@Valid JobCollectPageReqVO pageVO) {
        LambdaQueryWrapper<JobCollectDO> wrapper = new LambdaQueryWrapper<JobCollectDO>()
                .orderByDesc(JobCollectDO::getCreateTime);
        if (pageVO.getUserId() != null) {
            wrapper.eq(JobCollectDO::getUserId, pageVO.getUserId());
        }
        if (pageVO.getJobId() != null) {
            wrapper.eq(JobCollectDO::getJobId, pageVO.getJobId());
        }
        Page<JobCollectDO> page = jobCollectMapper.selectPage(
                new Page<>(pageVO.getPageNo(), pageVO.getPageSize()), wrapper);
        List<JobCollectRespVO> list = page.getRecords().stream().map(d -> {
            JobCollectRespVO vo = new JobCollectRespVO();
            BeanUtils.copyProperties(d, vo);
            return vo;
        }).collect(Collectors.toList());
        return success(new PageResult<>(list, page.getTotal()));
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除职位收藏记录")
    @Parameter(name = "id", description = "收藏编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hanzhong:job-collect:delete')")
    public CommonResult<Boolean> deleteJobCollect(@RequestParam("id") Long id) {
        jobCollectMapper.deleteById(id);
        return success(true);
    }

}
