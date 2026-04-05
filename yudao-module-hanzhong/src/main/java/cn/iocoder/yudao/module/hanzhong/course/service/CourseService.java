package cn.iocoder.yudao.module.hanzhong.course.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.course.controller.admin.vo.CourseCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.course.controller.admin.vo.CoursePageReqVO;
import cn.iocoder.yudao.module.hanzhong.course.controller.admin.vo.CourseUpdateReqVO;
import cn.iocoder.yudao.module.hanzhong.course.controller.app.vo.AppCoursePageReqVO;
import cn.iocoder.yudao.module.hanzhong.course.dal.dataobject.CourseDO;

/**
 * 汉中 课程 Service 接口
 *
 * @author hanzhong
 */
public interface CourseService {

    /**
     * 创建课程
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCourse(CourseCreateReqVO createReqVO);

    /**
     * 更新课程
     *
     * @param updateReqVO 更新信息
     */
    void updateCourse(CourseUpdateReqVO updateReqVO);

    /**
     * 更新课程状态
     *
     * @param id     课程编号
     * @param status 状态
     */
    void updateCourseStatus(Long id, Integer status);

    /**
     * 删除课程
     *
     * @param id 编号
     */
    void deleteCourse(Long id);

    /**
     * 获得课程
     *
     * @param id 编号
     * @return 课程
     */
    CourseDO getCourse(Long id);

    /**
     * 获得课程分页
     *
     * @param pageReqVO 分页查询
     * @return 课程分页
     */
    PageResult<CourseDO> getCoursePage(CoursePageReqVO pageReqVO);

    /**
     * 获得课程分页（App 使用）
     *
     * @param pageReqVO 分页查询
     * @return 课程分页
     */
    PageResult<CourseDO> getCoursePageForApp(AppCoursePageReqVO pageReqVO);

    /**
     * 增加课程浏览量
     *
     * @param id 课程编号
     */
    void incrementViewCount(Long id);

    /**
     * 增加课程报名人数
     *
     * @param id 课程编号
     */
    void incrementEnrollCount(Long id);

}
