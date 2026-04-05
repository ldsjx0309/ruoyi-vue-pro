package cn.iocoder.yudao.module.hanzhong.courseorder.convert;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.courseorder.controller.admin.vo.CourseOrderRespVO;
import cn.iocoder.yudao.module.hanzhong.courseorder.controller.app.vo.AppCourseOrderRespVO;
import cn.iocoder.yudao.module.hanzhong.courseorder.dal.dataobject.CourseOrderDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 汉中 课程订单 Convert
 *
 * @author hanzhong
 */
@Mapper
public interface CourseOrderConvert {

    CourseOrderConvert INSTANCE = Mappers.getMapper(CourseOrderConvert.class);

    CourseOrderRespVO convert(CourseOrderDO order);

    PageResult<CourseOrderRespVO> convertPage(PageResult<CourseOrderDO> pageResult);

    AppCourseOrderRespVO convertApp(CourseOrderDO order);

    PageResult<AppCourseOrderRespVO> convertAppPage(PageResult<CourseOrderDO> pageResult);

}
