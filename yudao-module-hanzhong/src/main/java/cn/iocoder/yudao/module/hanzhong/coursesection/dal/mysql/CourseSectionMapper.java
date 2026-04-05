package cn.iocoder.yudao.module.hanzhong.coursesection.dal.mysql;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.hanzhong.coursesection.controller.admin.vo.CourseSectionPageReqVO;
import cn.iocoder.yudao.module.hanzhong.coursesection.dal.dataobject.CourseSectionDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 汉中 课程章节 Mapper
 *
 * @author hanzhong
 */
@Mapper
public interface CourseSectionMapper extends BaseMapperX<CourseSectionDO> {

    default List<CourseSectionDO> selectByCourseId(Long courseId) {
        return selectList(new LambdaQueryWrapper<CourseSectionDO>()
                .eq(CourseSectionDO::getCourseId, courseId)
                .eq(CourseSectionDO::getStatus, 0)
                .orderByAsc(CourseSectionDO::getSort));
    }

    default PageResult<CourseSectionDO> selectPage(CourseSectionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CourseSectionDO>()
                .eqIfPresent(CourseSectionDO::getCourseId, reqVO.getCourseId())
                .eqIfPresent(CourseSectionDO::getStatus, reqVO.getStatus())
                .likeIfPresent(CourseSectionDO::getTitle, reqVO.getTitle())
                .orderByAsc(CourseSectionDO::getCourseId)
                .orderByAsc(CourseSectionDO::getSort));
    }

}
