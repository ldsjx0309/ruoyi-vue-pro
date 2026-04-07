package cn.iocoder.yudao.module.hanzhong.jobapply.dal.mysql;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.hanzhong.jobapply.controller.admin.vo.JobApplyPageReqVO;
import cn.iocoder.yudao.module.hanzhong.jobapply.controller.app.vo.AppJobApplyPageReqVO;
import cn.iocoder.yudao.module.hanzhong.jobapply.dal.dataobject.JobApplyDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 汉中 职位申请 Mapper
 *
 * @author hanzhong
 */
@Mapper
public interface JobApplyMapper extends BaseMapperX<JobApplyDO> {

    default PageResult<JobApplyDO> selectPage(JobApplyPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<JobApplyDO>()
                .eqIfPresent(JobApplyDO::getUserId, reqVO.getUserId())
                .eqIfPresent(JobApplyDO::getJobId, reqVO.getJobId())
                .eqIfPresent(JobApplyDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(JobApplyDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(JobApplyDO::getCreateTime));
    }

    default PageResult<JobApplyDO> selectPageByUserId(AppJobApplyPageReqVO reqVO, Long userId) {
        return selectPage(reqVO, new LambdaQueryWrapperX<JobApplyDO>()
                .eq(JobApplyDO::getUserId, userId)
                .orderByDesc(JobApplyDO::getCreateTime));
    }

    /**
     * 查询用户对某职位的已有申请（排除不合适的）
     */
    default JobApplyDO selectActiveByUserIdAndJobId(Long userId, Long jobId) {
        return selectOne(new LambdaQueryWrapperX<JobApplyDO>()
                .eq(JobApplyDO::getUserId, userId)
                .eq(JobApplyDO::getJobId, jobId)
                .ne(JobApplyDO::getStatus, 3) // 排除"不合适"状态，允许重新投递
                .last("LIMIT 1"));
    }

    /**
     * 查询指定职位的所有申请（管理员使用）
     */
    default List<JobApplyDO> selectListByJobId(Long jobId) {
        return selectList(new LambdaQueryWrapperX<JobApplyDO>()
                .eq(JobApplyDO::getJobId, jobId)
                .orderByDesc(JobApplyDO::getCreateTime));
    }

    /**
     * 按状态聚合统计职位申请数量（单次 GROUP BY 查询，替代多次 COUNT 查询）
     *
     * @return List of Map 每项包含 "status" (Integer) 和 "cnt" (Long)
     */
    @Select("SELECT status, COUNT(*) AS cnt FROM hanzhong_job_apply WHERE deleted = 0 GROUP BY status")
    List<Map<String, Object>> selectCountGroupByStatus();

}
