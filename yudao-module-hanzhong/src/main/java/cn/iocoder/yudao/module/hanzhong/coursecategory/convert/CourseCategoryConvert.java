package cn.iocoder.yudao.module.hanzhong.coursecategory.convert;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.coursecategory.controller.admin.vo.CourseCategoryCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.coursecategory.controller.admin.vo.CourseCategoryRespVO;
import cn.iocoder.yudao.module.hanzhong.coursecategory.controller.admin.vo.CourseCategoryUpdateReqVO;
import cn.iocoder.yudao.module.hanzhong.coursecategory.controller.app.vo.AppCourseCategoryRespVO;
import cn.iocoder.yudao.module.hanzhong.coursecategory.dal.dataobject.CourseCategoryDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 汉中 课程分类 Convert
 *
 * @author hanzhong
 */
@Mapper
public interface CourseCategoryConvert {

    CourseCategoryConvert INSTANCE = Mappers.getMapper(CourseCategoryConvert.class);

    CourseCategoryDO convert(CourseCategoryCreateReqVO createReqVO);

    CourseCategoryDO convert(CourseCategoryUpdateReqVO updateReqVO);

    CourseCategoryRespVO convert(CourseCategoryDO courseCategory);

    List<CourseCategoryRespVO> convertList(List<CourseCategoryDO> list);

    PageResult<CourseCategoryRespVO> convertPage(PageResult<CourseCategoryDO> pageResult);

    List<AppCourseCategoryRespVO> convertAppList(List<CourseCategoryDO> list);

}
