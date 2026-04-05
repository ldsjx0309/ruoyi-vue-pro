package cn.iocoder.yudao.module.hanzhong.job.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.job.controller.admin.vo.JobCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.job.controller.admin.vo.JobPageReqVO;
import cn.iocoder.yudao.module.hanzhong.job.controller.admin.vo.JobUpdateReqVO;
import cn.iocoder.yudao.module.hanzhong.job.controller.app.vo.AppJobPageReqVO;
import cn.iocoder.yudao.module.hanzhong.job.dal.dataobject.JobDO;

/**
 * 汉中 职位 Service 接口
 *
 * @author hanzhong
 */
public interface JobService {

    /**
     * 创建职位
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createJob(JobCreateReqVO createReqVO);

    /**
     * 更新职位
     *
     * @param updateReqVO 更新信息
     */
    void updateJob(JobUpdateReqVO updateReqVO);

    /**
     * 更新职位状态
     *
     * @param id     职位编号
     * @param status 状态
     */
    void updateJobStatus(Long id, Integer status);

    /**
     * 删除职位
     *
     * @param id 编号
     */
    void deleteJob(Long id);

    /**
     * 获得职位
     *
     * @param id 编号
     * @return 职位
     */
    JobDO getJob(Long id);

    /**
     * 获得职位分页
     *
     * @param pageReqVO 分页查询
     * @return 职位分页
     */
    PageResult<JobDO> getJobPage(JobPageReqVO pageReqVO);

    /**
     * 获得职位分页（App 使用）
     *
     * @param pageReqVO 分页查询
     * @return 职位分页
     */
    PageResult<JobDO> getJobPageForApp(AppJobPageReqVO pageReqVO);

}
