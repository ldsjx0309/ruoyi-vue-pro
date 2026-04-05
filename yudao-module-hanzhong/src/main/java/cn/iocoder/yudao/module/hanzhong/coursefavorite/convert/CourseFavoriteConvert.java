package cn.iocoder.yudao.module.hanzhong.coursefavorite.convert;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.coursefavorite.controller.app.vo.AppCourseFavoriteRespVO;
import cn.iocoder.yudao.module.hanzhong.coursefavorite.dal.dataobject.CourseFavoriteDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 汉中 课程收藏 Convert
 *
 * @author hanzhong
 */
@Mapper
public interface CourseFavoriteConvert {

    CourseFavoriteConvert INSTANCE = Mappers.getMapper(CourseFavoriteConvert.class);

    AppCourseFavoriteRespVO convertApp(CourseFavoriteDO bean);

    PageResult<AppCourseFavoriteRespVO> convertAppPage(PageResult<CourseFavoriteDO> page);

}
