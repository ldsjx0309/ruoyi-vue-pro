package cn.iocoder.yudao.module.hanzhong.job.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.job.controller.admin.vo.JobCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.job.controller.admin.vo.JobPageReqVO;
import cn.iocoder.yudao.module.hanzhong.job.controller.admin.vo.JobUpdateReqVO;
import cn.iocoder.yudao.module.hanzhong.job.controller.app.vo.AppJobPageReqVO;
import cn.iocoder.yudao.module.hanzhong.job.convert.JobConvert;
import cn.iocoder.yudao.module.hanzhong.job.dal.dataobject.JobDO;
import cn.iocoder.yudao.module.hanzhong.job.dal.mysql.JobMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.hanzhong.enums.ErrorCodeConstants.JOB_NOT_EXISTS;

/**
 * 汉中 职位 Service 实现类
 *
 * @author hanzhong
 */
@Service
@Validated
public class JobServiceImpl implements JobService {

    @Resource
    private JobMapper jobMapper;

    @Override
    public Long createJob(JobCreateReqVO createReqVO) {
        JobDO job = JobConvert.INSTANCE.convert(createReqVO);
        jobMapper.insert(job);
        return job.getId();
    }

    @Override
    public void updateJob(JobUpdateReqVO updateReqVO) {
        validateJobExists(updateReqVO.getId());
        JobDO updateObj = JobConvert.INSTANCE.convert(updateReqVO);
        jobMapper.updateById(updateObj);
    }

    @Override
    public void updateJobStatus(Long id, Integer status) {
        validateJobExists(id);
        JobDO updateObj = new JobDO();
        updateObj.setId(id);
        updateObj.setStatus(status);
        jobMapper.updateById(updateObj);
    }

    @Override
    public void deleteJob(Long id) {
        validateJobExists(id);
        jobMapper.deleteById(id);
    }

    private void validateJobExists(Long id) {
        if (jobMapper.selectById(id) == null) {
            throw exception(JOB_NOT_EXISTS);
        }
    }

    @Override
    public JobDO getJob(Long id) {
        return jobMapper.selectById(id);
    }

    @Override
    public PageResult<JobDO> getJobPage(JobPageReqVO pageReqVO) {
        return jobMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<JobDO> getJobPageForApp(AppJobPageReqVO pageReqVO) {
        return jobMapper.selectPageForApp(pageReqVO);
    }

}
