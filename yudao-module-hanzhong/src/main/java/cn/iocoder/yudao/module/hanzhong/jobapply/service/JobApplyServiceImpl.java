package cn.iocoder.yudao.module.hanzhong.jobapply.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.job.dal.dataobject.JobDO;
import cn.iocoder.yudao.module.hanzhong.job.dal.mysql.JobMapper;
import cn.iocoder.yudao.module.hanzhong.jobapply.controller.admin.vo.JobApplyPageReqVO;
import cn.iocoder.yudao.module.hanzhong.jobapply.controller.app.vo.AppJobApplyCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.jobapply.controller.app.vo.AppJobApplyPageReqVO;
import cn.iocoder.yudao.module.hanzhong.jobapply.dal.dataobject.JobApplyDO;
import cn.iocoder.yudao.module.hanzhong.jobapply.dal.mysql.JobApplyMapper;
import cn.iocoder.yudao.module.hanzhong.message.service.MessageService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.hanzhong.enums.ErrorCodeConstants.JOB_ALREADY_APPLIED;
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

    @Resource
    @Lazy
    private MessageService messageService;

    @Override
    public Long createJobApply(Long userId, AppJobApplyCreateReqVO createReqVO) {
        JobDO job = jobMapper.selectById(createReqVO.getJobId());
        if (job == null) {
            throw exception(JOB_NOT_EXISTS);
        }
        // 校验是否已投递过该职位（排除"不合适"状态，可重新投递）
        if (jobApplyMapper.selectActiveByUserIdAndJobId(userId, createReqVO.getJobId()) != null) {
            throw exception(JOB_ALREADY_APPLIED);
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
        JobApplyDO apply = jobApplyMapper.selectById(id);
        if (apply == null) {
            throw exception(JOB_APPLY_NOT_EXISTS);
        }
        JobApplyDO updateObj = new JobApplyDO();
        updateObj.setId(id);
        updateObj.setStatus(status);
        jobApplyMapper.updateById(updateObj);
        // 状态变更后，发送通知给投递用户
        if (status != null) {
            sendJobApplyStatusMessage(apply, status);
        }
    }

    /**
     * 根据职位申请状态变更发送通知消息
     */
    private void sendJobApplyStatusMessage(JobApplyDO apply, Integer status) {
        String jobInfo = "《" + apply.getJobTitle() + "》（" + apply.getCompany() + "）";
        String title = null;
        String content = null;
        switch (status) {
            case 1:
                title = "简历已被查看";
                content = "您投递的职位" + jobInfo + "简历已被查看，请耐心等待。";
                break;
            case 2:
                title = "收到面试邀请";
                content = "恭喜！您投递的职位" + jobInfo + "邀请您参加面试，请及时确认。";
                break;
            case 3:
                title = "投递结果通知";
                content = "您投递的职位" + jobInfo + "暂不符合要求，感谢您的关注。";
                break;
            case 4:
                title = "录用通知";
                content = "恭喜您！您投递的职位" + jobInfo + "已录用，请及时联系HR。";
                break;
            default:
                break;
        }
        if (title != null) {
            messageService.sendSystemMessage(apply.getUserId(), title, content);
        }
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
