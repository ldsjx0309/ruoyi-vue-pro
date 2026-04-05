package cn.iocoder.yudao.module.hanzhong.course.convert;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.course.controller.admin.vo.CourseCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.course.controller.admin.vo.CourseRespVO;
import cn.iocoder.yudao.module.hanzhong.course.controller.admin.vo.CourseUpdateReqVO;
import cn.iocoder.yudao.module.hanzhong.course.controller.app.vo.AppCourseRespVO;
import cn.iocoder.yudao.module.hanzhong.course.dal.dataobject.CourseDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 汉中 课程 Convert
 *
 * @author hanzhong
 */
@Mapper
public interface CourseConvert {

    CourseConvert INSTANCE = Mappers.getMapper(CourseConvert.class);

    CourseDO convert(CourseCreateReqVO createReqVO);

    CourseDO convert(CourseUpdateReqVO updateReqVO);

    CourseRespVO convert(CourseDO course);

    PageResult<CourseRespVO> convertPage(PageResult<CourseDO> pageResult);

    AppCourseRespVO convertApp(CourseDO course);

    List<AppCourseRespVO> convertAppList(List<CourseDO> list);

    PageResult<AppCourseRespVO> convertAppPage(PageResult<CourseDO> pageResult);

}
