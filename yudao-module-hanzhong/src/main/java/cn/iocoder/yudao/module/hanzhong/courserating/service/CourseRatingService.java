package cn.iocoder.yudao.module.hanzhong.courserating.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.module.hanzhong.courserating.controller.admin.vo.CourseRatingPageReqVO;
import cn.iocoder.yudao.module.hanzhong.courserating.controller.app.vo.AppCourseRatingCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.courserating.controller.app.vo.AppCourseRatingRespVO;
import cn.iocoder.yudao.module.hanzhong.courserating.dal.dataobject.CourseRatingDO;

/**
 * 汉中 课程评分 Service 接口
 *
 * @author hanzhong
 */
public interface CourseRatingService {

    /**
     * 创建或更新课程评分（一个用户对同一课程只能有一条评分记录）
     *
     * @param userId    登录用户编号
     * @param createReqVO 请求 VO
     * @return 评分记录编号
     */
    Long createOrUpdateRating(Long userId, AppCourseRatingCreateReqVO createReqVO);

    /**
     * 获得当前用户对某课程的评分
     *
     * @param userId   用户编号
     * @param courseId 课程编号
     * @return 评分 VO，不存在时返回 null
     */
    AppCourseRatingRespVO getMyRating(Long userId, Long courseId);

    /**
     * 获得课程评分分页（App 用，公开可见）
     *
     * @param pageParam 分页参数
     * @param courseId  课程编号
     * @return 分页结果
     */
    PageResult<AppCourseRatingRespVO> getRatingPageByCourseId(PageParam pageParam, Long courseId);

    /**
     * 获得课程的平均评分和评分数量
     *
     * @param courseId 课程编号
     * @return 包含 avgRating 和 ratingCount 的信息（数组 [avgRating, ratingCount]）
     */
    double[] getAvgRatingAndCount(Long courseId);

    /**
     * 获得课程评分分页（管理后台）
     */
    PageResult<CourseRatingDO> getRatingAdminPage(CourseRatingPageReqVO pageReqVO);

    /**
     * 删除课程评分（管理员）
     *
     * @param id 评分编号
     */
    void deleteRating(Long id);

    /**
     * 删除用户自己的课程评分
     *
     * @param id     评分编号
     * @param userId 当前登录用户编号（用于校验归属）
     */
    void deleteOwnRating(Long id, Long userId);

}
