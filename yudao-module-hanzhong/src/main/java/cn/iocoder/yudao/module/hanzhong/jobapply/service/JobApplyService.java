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
     * 撤销职位申请（仅用户本人，且仅限已投递状态）
     */
    void withdrawJobApply(Long id, Long userId);

    /**
     * 更新申请状态（可同时更新备注）
     */
    void updateJobApplyStatus(Long id, Integer status, String remark);

    /**
     * 批量更新申请状态
     */
    void batchUpdateJobApplyStatus(java.util.List<Long> ids, Integer status, String remark);

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

    /**
     * 根据职位编号获取我的申请（有则返回，无则返回 null）
     *
     * @param userId 用户编号
     * @param jobId  职位编号
     * @return 申请记录，无则 null
     */
    JobApplyDO getMyApplyByJobId(Long userId, Long jobId);

    /**
     * 获得指定职位的所有申请列表（管理员使用，不分页）
     *
     * @param jobId 职位编号
     * @return 申请列表
     */
    java.util.List<JobApplyDO> getJobApplyListByJobId(Long jobId);

}
