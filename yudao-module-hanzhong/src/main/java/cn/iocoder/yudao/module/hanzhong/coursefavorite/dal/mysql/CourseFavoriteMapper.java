package cn.iocoder.yudao.module.hanzhong.coursefavorite.dal.mysql;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.hanzhong.coursefavorite.dal.dataobject.CourseFavoriteDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 汉中 课程收藏 Mapper
 *
 * @author hanzhong
 */
@Mapper
public interface CourseFavoriteMapper extends BaseMapperX<CourseFavoriteDO> {

    default CourseFavoriteDO selectByUserIdAndCourseId(Long userId, Long courseId) {
        return selectOne(new LambdaQueryWrapper<CourseFavoriteDO>()
                .eq(CourseFavoriteDO::getUserId, userId)
                .eq(CourseFavoriteDO::getCourseId, courseId));
    }

    default PageResult<CourseFavoriteDO> selectPageByUserId(PageParam pageParam, Long userId) {
        return selectPage(pageParam, new LambdaQueryWrapper<CourseFavoriteDO>()
                .eq(CourseFavoriteDO::getUserId, userId)
                .orderByDesc(CourseFavoriteDO::getCreateTime));
    }

}
