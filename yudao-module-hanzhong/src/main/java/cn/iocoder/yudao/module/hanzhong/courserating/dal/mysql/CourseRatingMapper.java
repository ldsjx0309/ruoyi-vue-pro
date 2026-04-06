package cn.iocoder.yudao.module.hanzhong.courserating.dal.mysql;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.hanzhong.courserating.dal.dataobject.CourseRatingDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 汉中 课程评分 Mapper
 *
 * @author hanzhong
 */
@Mapper
public interface CourseRatingMapper extends BaseMapperX<CourseRatingDO> {

    default CourseRatingDO selectByUserIdAndCourseId(Long userId, Long courseId) {
        return selectOne(new LambdaQueryWrapper<CourseRatingDO>()
                .eq(CourseRatingDO::getUserId, userId)
                .eq(CourseRatingDO::getCourseId, courseId)
                .last("LIMIT 1"));
    }

    default PageResult<CourseRatingDO> selectPageByCourseId(cn.iocoder.yudao.framework.common.pojo.PageParam pageParam,
                                                             Long courseId) {
        return selectPage(pageParam, new LambdaQueryWrapper<CourseRatingDO>()
                .eq(CourseRatingDO::getCourseId, courseId)
                .orderByDesc(CourseRatingDO::getCreateTime));
    }

    default PageResult<CourseRatingDO> selectAdminPage(
            cn.iocoder.yudao.module.hanzhong.courserating.controller.admin.vo.CourseRatingPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapper<CourseRatingDO>()
                .eq(reqVO.getCourseId() != null, CourseRatingDO::getCourseId, reqVO.getCourseId())
                .eq(reqVO.getUserId() != null, CourseRatingDO::getUserId, reqVO.getUserId())
                .eq(reqVO.getRating() != null, CourseRatingDO::getRating, reqVO.getRating())
                .orderByDesc(CourseRatingDO::getCreateTime));
    }

    /**
     * 查询课程的平均评分
     */
    @Select("SELECT AVG(rating) FROM hanzhong_course_rating WHERE course_id = #{courseId} AND deleted = 0")
    Double selectAvgRatingByCourseId(@Param("courseId") Long courseId);

    /**
     * 查询课程的评分数量
     */
    @Select("SELECT COUNT(*) FROM hanzhong_course_rating WHERE course_id = #{courseId} AND deleted = 0")
    Long selectCountByCourseId(@Param("courseId") Long courseId);

}
