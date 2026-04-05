package cn.iocoder.yudao.module.hanzhong.coursesection.convert;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.coursesection.controller.admin.vo.CourseSectionCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.coursesection.controller.admin.vo.CourseSectionRespVO;
import cn.iocoder.yudao.module.hanzhong.coursesection.controller.admin.vo.CourseSectionUpdateReqVO;
import cn.iocoder.yudao.module.hanzhong.coursesection.controller.app.vo.AppCourseSectionRespVO;
import cn.iocoder.yudao.module.hanzhong.coursesection.dal.dataobject.CourseSectionDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 汉中 课程章节 Convert
 *
 * @author hanzhong
 */
@Mapper
public interface CourseSectionConvert {

    CourseSectionConvert INSTANCE = Mappers.getMapper(CourseSectionConvert.class);

    CourseSectionDO convert(CourseSectionCreateReqVO bean);

    CourseSectionDO convert(CourseSectionUpdateReqVO bean);

    CourseSectionRespVO convert(CourseSectionDO bean);

    List<CourseSectionRespVO> convertList(List<CourseSectionDO> list);

    PageResult<CourseSectionRespVO> convertPage(PageResult<CourseSectionDO> page);

    AppCourseSectionRespVO convertApp(CourseSectionDO bean);

    List<AppCourseSectionRespVO> convertAppList(List<CourseSectionDO> list);

}
