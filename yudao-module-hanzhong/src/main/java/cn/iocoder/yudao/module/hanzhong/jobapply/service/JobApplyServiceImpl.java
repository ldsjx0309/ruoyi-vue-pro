package cn.iocoder.yudao.module.hanzhong.jobapply.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.job.dal.dataobject.JobDO;
import cn.iocoder.yudao.module.hanzhong.job.dal.mysql.JobMapper;
import cn.iocoder.yudao.module.hanzhong.jobapply.controller.admin.vo.JobApplyPageReqVO;
import cn.iocoder.yudao.module.hanzhong.jobapply.controller.app.vo.AppJobApplyCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.jobapply.controller.app.vo.AppJobApplyPageReqVO;
import cn.iocoder.yudao.module.hanzhong.jobapply.dal.dataobject.JobApplyDO;
import cn.iocoder.yudao.module.hanzhong.jobapply.dal.mysql.JobApplyMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.hanzhong.enums.ErrorCodeConstants.JOB_NOT_EXISTS;
import static cn.iocoder.yudao.module.hanzhong.enums.ErrorCodeConstants.JOB_APPLY_NOT_EXISTS;

/**
 * 汉中 职位申请 Service 实现类
 *
 * @author hanzhong
 */
@Service
@Validated
public class JobApplyServiceImpl implements JobApplyService {

    @Resource
    private JobApplyMapper jobApplyMapper;

    @Resource
    private JobMapper jobMapper;

    @Override
    public Long createJobApply(Long userId, AppJobApplyCreateReqVO createReqVO) {
        JobDO job = jobMapper.selectById(createReqVO.getJobId());
        if (job == null) {
            throw exception(JOB_NOT_EXISTS);
        }
        JobApplyDO apply = new JobApplyDO();
        apply.setUserId(userId);
        apply.setJobId(job.getId());
        apply.setJobTitle(job.getTitle());
        apply.setCompany(job.getCompany());
        apply.setResumeId(createReqVO.getResumeId());
        apply.setStatus(0);
        apply.setApplyTime(LocalDateTime.now());
        jobApplyMapper.insert(apply);
        return apply.getId();
    }

    @Override
    public void updateJobApplyStatus(Long id, Integer status) {
        if (jobApplyMapper.selectById(id) == null) {
            throw exception(JOB_APPLY_NOT_EXISTS);
        }
        JobApplyDO updateObj = new JobApplyDO();
        updateObj.setId(id);
        updateObj.setStatus(status);
        jobApplyMapper.updateById(updateObj);
    }

    @Override
    public JobApplyDO getJobApply(Long id) {
        return jobApplyMapper.selectById(id);
    }

    @Override
    public PageResult<JobApplyDO> getJobApplyPage(JobApplyPageReqVO pageReqVO) {
        return jobApplyMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<JobApplyDO> getMyJobApplyPage(AppJobApplyPageReqVO pageReqVO, Long userId) {
        return jobApplyMapper.selectPageByUserId(pageReqVO, userId);
    }

}
