package cn.iocoder.yudao.module.hanzhong.coursefavorite.dal.mysql;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.hanzhong.coursefavorite.controller.admin.vo.CourseFavoritePageReqVO;
import cn.iocoder.yudao.module.hanzhong.coursefavorite.dal.dataobject.CourseFavoriteDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
                .eq(CourseFavoriteDO::getCourseId, courseId)
                .last("LIMIT 1"));
    }

    default PageResult<CourseFavoriteDO> selectPageByUserId(PageParam pageParam, Long userId) {
        return selectPage(pageParam, new LambdaQueryWrapper<CourseFavoriteDO>()
                .eq(CourseFavoriteDO::getUserId, userId)
                .orderByDesc(CourseFavoriteDO::getCreateTime));
    }

    default PageResult<CourseFavoriteDO> selectAdminPage(CourseFavoritePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapper<CourseFavoriteDO>()
                .eq(reqVO.getUserId() != null, CourseFavoriteDO::getUserId, reqVO.getUserId())
                .eq(reqVO.getCourseId() != null, CourseFavoriteDO::getCourseId, reqVO.getCourseId())
                .like(reqVO.getCourseName() != null && !reqVO.getCourseName().isEmpty(),
                        CourseFavoriteDO::getCourseName, reqVO.getCourseName())
                .orderByDesc(CourseFavoriteDO::getCreateTime));
    }

    /**
     * 物理删除收藏记录，避免软删除后唯一键冲突（重新收藏时 INSERT 报重复键）
     */
    @Delete("DELETE FROM hanzhong_course_favorite WHERE user_id = #{userId} AND course_id = #{courseId}")
    int deleteByUserIdAndCourseId(@Param("userId") Long userId, @Param("courseId") Long courseId);

    /**
     * 按 ID 物理删除收藏记录（供管理员使用）
     */
    @Delete("DELETE FROM hanzhong_course_favorite WHERE id = #{id}")
    int deleteByIdPhysically(@Param("id") Long id);

    /**
     * 获取用户收藏的所有课程 ID 列表（用于课程列表页批量判断收藏状态）
     */
    @org.apache.ibatis.annotations.Select("SELECT course_id FROM hanzhong_course_favorite WHERE user_id = #{userId} AND deleted = 0")
    java.util.List<Long> selectCourseIdsByUserId(@Param("userId") Long userId);

}
