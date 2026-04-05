package cn.iocoder.yudao.module.hanzhong.resume.convert;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.resume.controller.admin.vo.ResumeRespVO;
import cn.iocoder.yudao.module.hanzhong.resume.controller.app.vo.AppResumeRespVO;
import cn.iocoder.yudao.module.hanzhong.resume.controller.app.vo.AppResumeSaveReqVO;
import cn.iocoder.yudao.module.hanzhong.resume.dal.dataobject.ResumeDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 汉中 简历 Convert
 *
 * @author hanzhong
 */
@Mapper
public interface ResumeConvert {

    ResumeConvert INSTANCE = Mappers.getMapper(ResumeConvert.class);

    ResumeDO convert(AppResumeSaveReqVO saveReqVO);

    ResumeRespVO convert(ResumeDO resume);

    PageResult<ResumeRespVO> convertPage(PageResult<ResumeDO> pageResult);

    AppResumeRespVO convertApp(ResumeDO resume);

}
