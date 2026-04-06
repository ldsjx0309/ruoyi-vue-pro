package cn.iocoder.yudao.module.hanzhong.courseorder.dal.mysql;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.hanzhong.courseorder.controller.admin.vo.CourseOrderPageReqVO;
import cn.iocoder.yudao.module.hanzhong.courseorder.controller.app.vo.AppCourseOrderPageReqVO;
import cn.iocoder.yudao.module.hanzhong.courseorder.dal.dataobject.CourseOrderDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 汉中 课程订单 Mapper
 *
 * @author hanzhong
 */
@Mapper
public interface CourseOrderMapper extends BaseMapperX<CourseOrderDO> {

    default PageResult<CourseOrderDO> selectPage(CourseOrderPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CourseOrderDO>()
                .eqIfPresent(CourseOrderDO::getUserId, reqVO.getUserId())
                .eqIfPresent(CourseOrderDO::getCourseId, reqVO.getCourseId())
                .eqIfPresent(CourseOrderDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(CourseOrderDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CourseOrderDO::getCreateTime));
    }

    default PageResult<CourseOrderDO> selectPageByUserId(AppCourseOrderPageReqVO reqVO, Long userId) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CourseOrderDO>()
                .eq(CourseOrderDO::getUserId, userId)
                .eqIfPresent(CourseOrderDO::getStatus, reqVO.getStatus())
                .orderByDesc(CourseOrderDO::getCreateTime));
    }

    /**
     * 查询用户对某课程的有效订单（未取消）
     */
    default CourseOrderDO selectActiveByUserIdAndCourseId(Long userId, Long courseId) {
        return selectOne(new LambdaQueryWrapperX<CourseOrderDO>()
                .eq(CourseOrderDO::getUserId, userId)
                .eq(CourseOrderDO::getCourseId, courseId)
                .ne(CourseOrderDO::getStatus, 2) // 排除已取消订单
                .last("LIMIT 1"));
    }

}
