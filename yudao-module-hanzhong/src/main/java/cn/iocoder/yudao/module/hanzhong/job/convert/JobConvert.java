package cn.iocoder.yudao.module.hanzhong.job.convert;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.job.controller.admin.vo.JobCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.job.controller.admin.vo.JobRespVO;
import cn.iocoder.yudao.module.hanzhong.job.controller.admin.vo.JobUpdateReqVO;
import cn.iocoder.yudao.module.hanzhong.job.controller.app.vo.AppJobRespVO;
import cn.iocoder.yudao.module.hanzhong.job.dal.dataobject.JobDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 汉中 职位 Convert
 *
 * @author hanzhong
 */
@Mapper
public interface JobConvert {

    JobConvert INSTANCE = Mappers.getMapper(JobConvert.class);

    JobDO convert(JobCreateReqVO createReqVO);

    JobDO convert(JobUpdateReqVO updateReqVO);

    JobRespVO convert(JobDO job);

    PageResult<JobRespVO> convertPage(PageResult<JobDO> pageResult);

    @Mapping(target = "hasApplied", ignore = true)
    AppJobRespVO convertApp(JobDO job);

    PageResult<AppJobRespVO> convertAppPage(PageResult<JobDO> pageResult);

}
