package cn.iocoder.yudao.module.hanzhong.job.dal.mysql;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.hanzhong.job.controller.admin.vo.JobPageReqVO;
import cn.iocoder.yudao.module.hanzhong.job.controller.app.vo.AppJobPageReqVO;
import cn.iocoder.yudao.module.hanzhong.job.dal.dataobject.JobDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 汉中 职位 Mapper
 *
 * @author hanzhong
 */
@Mapper
public interface JobMapper extends BaseMapperX<JobDO> {

    default PageResult<JobDO> selectPage(JobPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<JobDO>()
                .likeIfPresent(JobDO::getTitle, reqVO.getTitle())
                .eqIfPresent(JobDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(JobDO::getCreateTime, reqVO.getCreateTime())
                .orderByAsc(JobDO::getSort));
    }

    default PageResult<JobDO> selectPageForApp(AppJobPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<JobDO>()
                .eq(JobDO::getStatus, CommonStatusEnum.ENABLE.getStatus())
                .likeIfPresent(JobDO::getTitle, reqVO.getTitle())
                .eqIfPresent(JobDO::getCategory, reqVO.getCategory())
                .likeIfPresent(JobDO::getLocation, reqVO.getLocation())
                .orderByAsc(JobDO::getSort));
    }

}
