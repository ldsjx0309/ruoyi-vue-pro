package cn.iocoder.yudao.module.hanzhong.courseorder.dal.mysql;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.hanzhong.courseorder.controller.admin.vo.CourseOrderPageReqVO;
import cn.iocoder.yudao.module.hanzhong.courseorder.controller.app.vo.AppCourseOrderPageReqVO;
import cn.iocoder.yudao.module.hanzhong.courseorder.dal.dataobject.CourseOrderDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

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

    /**
     * 按状态聚合统计课程订单数量（单次 GROUP BY 查询）
     *
     * @return List of Map，每项包含 "status" (Integer) 和 "cnt" (Long)
     */
    @Select("SELECT status, COUNT(*) AS cnt FROM hanzhong_course_order WHERE deleted = 0 GROUP BY status")
    List<Map<String, Object>> selectCountGroupByStatus();

    /**
     * 统计已支付订单的总收入（分）
     */
    @Select("SELECT COALESCE(SUM(price), 0) FROM hanzhong_course_order WHERE status = 1 AND deleted = 0")
    Long selectTotalIncome();

    /**
     * 统计本月已支付订单收入（分）
     */
    @Select("SELECT COALESCE(SUM(price), 0) FROM hanzhong_course_order WHERE status = 1 AND deleted = 0 AND DATE_FORMAT(pay_time, '%Y-%m') = DATE_FORMAT(NOW(), '%Y-%m')")
    Long selectCurrentMonthIncome();

    /**
     * 统计上月已支付订单收入（分）
     */
    @Select("SELECT COALESCE(SUM(price), 0) FROM hanzhong_course_order WHERE status = 1 AND deleted = 0 AND DATE_FORMAT(pay_time, '%Y-%m') = DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 1 MONTH), '%Y-%m')")
    Long selectLastMonthIncome();

    /**
     * 统计今日已支付订单收入（分）
     */
    @Select("SELECT COALESCE(SUM(price), 0) FROM hanzhong_course_order WHERE status = 1 AND deleted = 0 AND DATE(pay_time) = CURDATE()")
    Long selectTodayIncome();

    /**
     * 统计指定日期范围内的每日收入
     *
     * @param startDate 起始日期 (yyyy-MM-dd)
     * @param endDate   结束日期 (yyyy-MM-dd)
     * @return List of Map 含 "date_str" 和 "income"
     */
    @Select("SELECT DATE_FORMAT(pay_time, '%m-%d') AS date_str, COALESCE(SUM(price), 0) AS income, COUNT(*) AS order_count FROM hanzhong_course_order WHERE status = 1 AND deleted = 0 AND DATE(pay_time) BETWEEN #{startDate} AND #{endDate} GROUP BY DATE(pay_time) ORDER BY DATE(pay_time)")
    List<Map<String, Object>> selectDailyIncomeByDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate);

}
