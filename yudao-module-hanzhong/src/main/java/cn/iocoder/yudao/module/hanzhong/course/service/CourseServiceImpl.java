package cn.iocoder.yudao.module.hanzhong.course.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.course.controller.admin.vo.CourseCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.course.controller.admin.vo.CoursePageReqVO;
import cn.iocoder.yudao.module.hanzhong.course.controller.admin.vo.CourseUpdateReqVO;
import cn.iocoder.yudao.module.hanzhong.course.controller.app.vo.AppCoursePageReqVO;
import cn.iocoder.yudao.module.hanzhong.course.convert.CourseConvert;
import cn.iocoder.yudao.module.hanzhong.course.dal.dataobject.CourseDO;
import cn.iocoder.yudao.module.hanzhong.course.dal.mysql.CourseMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.hanzhong.enums.ErrorCodeConstants.COURSE_NOT_EXISTS;

/**
 * 汉中 课程 Service 实现类
 *
 * @author hanzhong
 */
@Service
@Validated
public class CourseServiceImpl implements CourseService {

    @Resource
    private CourseMapper courseMapper;

    @Override
    public Long createCourse(CourseCreateReqVO createReqVO) {
        CourseDO course = CourseConvert.INSTANCE.convert(createReqVO);
        if (course.getPrice() == null) {
            course.setPrice(0);
        }
        if (course.getViewCount() == null) {
            course.setViewCount(0);
        }
        if (course.getEnrollCount() == null) {
            course.setEnrollCount(0);
        }
        courseMapper.insert(course);
        return course.getId();
    }

    @Override
    public void updateCourse(CourseUpdateReqVO updateReqVO) {
        validateCourseExists(updateReqVO.getId());
        CourseDO updateObj = CourseConvert.INSTANCE.convert(updateReqVO);
        courseMapper.updateById(updateObj);
    }

    @Override
    public void updateCourseStatus(Long id, Integer status) {
        validateCourseExists(id);
        CourseDO updateObj = new CourseDO();
        updateObj.setId(id);
        updateObj.setStatus(status);
        courseMapper.updateById(updateObj);
    }

    @Override
    public void deleteCourse(Long id) {
        validateCourseExists(id);
        courseMapper.deleteById(id);
    }

    private void validateCourseExists(Long id) {
        if (courseMapper.selectById(id) == null) {
            throw exception(COURSE_NOT_EXISTS);
        }
    }

    @Override
    public CourseDO getCourse(Long id) {
        return courseMapper.selectById(id);
    }

    @Override
    public void incrementViewCount(Long id) {
        courseMapper.incrementViewCount(id);
    }

    @Override
    public void incrementEnrollCount(Long id) {
        courseMapper.incrementEnrollCount(id);
    }

    @Override
    public PageResult<CourseDO> getCoursePage(CoursePageReqVO pageReqVO) {
        return courseMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<CourseDO> getCoursePageForApp(AppCoursePageReqVO pageReqVO) {
        return courseMapper.selectPageForApp(pageReqVO);
    }

    @Override
    public List<CourseDO> getEnabledCourseList() {
        return courseMapper.selectListByStatus(0);
    }

}
