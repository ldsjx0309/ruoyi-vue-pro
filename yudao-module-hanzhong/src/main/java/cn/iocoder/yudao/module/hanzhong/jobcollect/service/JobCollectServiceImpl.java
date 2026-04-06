package cn.iocoder.yudao.module.hanzhong.jobcollect.service;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.job.dal.dataobject.JobDO;
import cn.iocoder.yudao.module.hanzhong.job.dal.mysql.JobMapper;
import cn.iocoder.yudao.module.hanzhong.jobcollect.dal.dataobject.JobCollectDO;
import cn.iocoder.yudao.module.hanzhong.jobcollect.dal.mysql.JobCollectMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

/**
 * 汉中 职位收藏 Service 实现类
 *
 * @author hanzhong
 */
@Service
@Validated
public class JobCollectServiceImpl implements JobCollectService {

    @Resource
    private JobCollectMapper jobCollectMapper;

    @Resource
    private JobMapper jobMapper;

    @Override
    public boolean toggleCollect(Long userId, Long jobId) {
        JobCollectDO existing = jobCollectMapper.selectByUserIdAndJobId(userId, jobId);
        if (existing != null) {
            // 已收藏 → 取消收藏（物理删除，保证唯一键不冲突）
            jobCollectMapper.physicalDeleteByUserIdAndJobId(userId, jobId);
            return false;
        }
        // 未收藏 → 收藏
        JobDO job = jobMapper.selectById(jobId);
        JobCollectDO collect = new JobCollectDO();
        collect.setUserId(userId);
        collect.setJobId(jobId);
        if (job != null) {
            collect.setJobTitle(job.getTitle());
            collect.setCompany(job.getCompany());
        }
        jobCollectMapper.insert(collect);
        return true;
    }

    @Override
    public boolean isCollected(Long userId, Long jobId) {
        return jobCollectMapper.selectByUserIdAndJobId(userId, jobId) != null;
    }

    @Override
    public PageResult<JobCollectDO> getMyCollectPage(PageParam pageParam, Long userId) {
        Page<JobCollectDO> page = jobCollectMapper.selectPage(
                new Page<>(pageParam.getPageNo(), pageParam.getPageSize()),
                new LambdaQueryWrapper<JobCollectDO>()
                        .eq(JobCollectDO::getUserId, userId)
                        .orderByDesc(JobCollectDO::getCreateTime));
        return new PageResult<>(page.getRecords(), page.getTotal());
    }

    @Override
    public void deleteCollect(Long id, Long userId) {
        JobCollectDO collect = jobCollectMapper.selectById(id);
        if (collect != null && collect.getUserId().equals(userId)) {
            jobCollectMapper.physicalDeleteByUserIdAndJobId(userId, collect.getJobId());
        }
    }

}
