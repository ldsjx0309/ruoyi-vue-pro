package cn.iocoder.yudao.module.hanzhong.jobapply.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.jobapply.controller.admin.vo.JobApplyPageReqVO;
import cn.iocoder.yudao.module.hanzhong.jobapply.controller.app.vo.AppJobApplyCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.jobapply.controller.app.vo.AppJobApplyPageReqVO;
import cn.iocoder.yudao.module.hanzhong.jobapply.dal.dataobject.JobApplyDO;

/**
 * 汉中 职位申请 Service 接口
 *
 * @author hanzhong
 */
public interface JobApplyService {

    /**
     * 创建申请
     */
    Long createJobApply(Long userId, AppJobApplyCreateReqVO createReqVO);

    /**
     * 更新申请状态
     */
    void updateJobApplyStatus(Long id, Integer status);

    /**
     * 获得申请
     */
    JobApplyDO getJobApply(Long id);

    /**
     * 获得申请分页（管理员）
     */
    PageResult<JobApplyDO> getJobApplyPage(JobApplyPageReqVO pageReqVO);

    /**
     * 获得我的申请分页
     */
    PageResult<JobApplyDO> getMyJobApplyPage(AppJobApplyPageReqVO pageReqVO, Long userId);

}
