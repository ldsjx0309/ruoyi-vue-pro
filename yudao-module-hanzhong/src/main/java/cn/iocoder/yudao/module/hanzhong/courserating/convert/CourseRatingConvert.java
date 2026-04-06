package cn.iocoder.yudao.module.hanzhong.courserating.convert;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.courserating.controller.admin.vo.CourseRatingRespVO;
import cn.iocoder.yudao.module.hanzhong.courserating.controller.app.vo.AppCourseRatingRespVO;
import cn.iocoder.yudao.module.hanzhong.courserating.dal.dataobject.CourseRatingDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 汉中 课程评分 Convert
 *
 * @author hanzhong
 */
@Mapper
public interface CourseRatingConvert {

    CourseRatingConvert INSTANCE = Mappers.getMapper(CourseRatingConvert.class);

    AppCourseRatingRespVO convertApp(CourseRatingDO bean);

    PageResult<AppCourseRatingRespVO> convertAppPage(PageResult<CourseRatingDO> page);

    CourseRatingRespVO convert(CourseRatingDO bean);

    PageResult<CourseRatingRespVO> convertPage(PageResult<CourseRatingDO> page);

}
