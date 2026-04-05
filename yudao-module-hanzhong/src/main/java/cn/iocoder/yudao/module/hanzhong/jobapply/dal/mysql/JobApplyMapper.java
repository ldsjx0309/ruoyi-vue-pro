package cn.iocoder.yudao.module.hanzhong.jobapply.dal.mysql;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.hanzhong.jobapply.controller.admin.vo.JobApplyPageReqVO;
import cn.iocoder.yudao.module.hanzhong.jobapply.controller.app.vo.AppJobApplyPageReqVO;
import cn.iocoder.yudao.module.hanzhong.jobapply.dal.dataobject.JobApplyDO;
import org.apache.ibatis.annotations.Mapper;

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

}
