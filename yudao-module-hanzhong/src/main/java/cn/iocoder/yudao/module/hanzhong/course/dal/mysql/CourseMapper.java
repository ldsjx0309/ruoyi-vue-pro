package cn.iocoder.yudao.module.hanzhong.course.dal.mysql;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.hanzhong.course.controller.admin.vo.CoursePageReqVO;
import cn.iocoder.yudao.module.hanzhong.course.controller.app.vo.AppCoursePageReqVO;
import cn.iocoder.yudao.module.hanzhong.course.dal.dataobject.CourseDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 汉中 课程 Mapper
 *
 * @author hanzhong
 */
@Mapper
public interface CourseMapper extends BaseMapperX<CourseDO> {

    @Update("UPDATE hanzhong_course SET view_count = view_count + 1 WHERE id = #{id} AND deleted = 0")
    int incrementViewCount(@Param("id") Long id);

    @Update("UPDATE hanzhong_course SET enroll_count = enroll_count + 1 WHERE id = #{id} AND deleted = 0")
    int incrementEnrollCount(@Param("id") Long id);

    default PageResult<CourseDO> selectPage(CoursePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CourseDO>()
                .likeIfPresent(CourseDO::getTitle, reqVO.getTitle())
                .eqIfPresent(CourseDO::getCategoryId, reqVO.getCategoryId())
                .eqIfPresent(CourseDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(CourseDO::getCreateTime, reqVO.getCreateTime())
                .orderByAsc(CourseDO::getSort));
    }

    default PageResult<CourseDO> selectPageForApp(AppCoursePageReqVO reqVO) {
        LambdaQueryWrapperX<CourseDO> wrapper = new LambdaQueryWrapperX<CourseDO>()
                .eq(CourseDO::getStatus, CommonStatusEnum.ENABLE.getStatus())
                .likeIfPresent(CourseDO::getTitle, reqVO.getTitle())
                .eqIfPresent(CourseDO::getCategoryId, reqVO.getCategoryId());
        if ("hot".equals(reqVO.getSortBy())) {
            wrapper.orderByDesc(CourseDO::getEnrollCount);
        } else if ("new".equals(reqVO.getSortBy())) {
            wrapper.orderByDesc(CourseDO::getCreateTime);
        } else {
            wrapper.orderByAsc(CourseDO::getSort);
        }
        return selectPage(reqVO, wrapper);
    }

}
