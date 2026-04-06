package cn.iocoder.yudao.module.hanzhong.jobapply.convert;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.jobapply.controller.admin.vo.JobApplyRespVO;
import cn.iocoder.yudao.module.hanzhong.jobapply.controller.app.vo.AppJobApplyRespVO;
import cn.iocoder.yudao.module.hanzhong.jobapply.dal.dataobject.JobApplyDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 汉中 职位申请 Convert
 *
 * @author hanzhong
 */
@Mapper
public interface JobApplyConvert {

    JobApplyConvert INSTANCE = Mappers.getMapper(JobApplyConvert.class);

    JobApplyRespVO convert(JobApplyDO apply);

    java.util.List<JobApplyRespVO> convertList(java.util.List<JobApplyDO> list);

    PageResult<JobApplyRespVO> convertPage(PageResult<JobApplyDO> pageResult);

    AppJobApplyRespVO convertApp(JobApplyDO apply);

    PageResult<AppJobApplyRespVO> convertAppPage(PageResult<JobApplyDO> pageResult);

}
