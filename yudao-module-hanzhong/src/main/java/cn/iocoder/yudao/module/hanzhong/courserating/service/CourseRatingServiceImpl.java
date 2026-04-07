package cn.iocoder.yudao.module.hanzhong.courserating.service;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.course.dal.dataobject.CourseDO;
import cn.iocoder.yudao.module.hanzhong.course.dal.mysql.CourseMapper;
import cn.iocoder.yudao.module.hanzhong.courseorder.dal.mysql.CourseOrderMapper;
import cn.iocoder.yudao.module.hanzhong.courserating.controller.admin.vo.CourseRatingPageReqVO;
import cn.iocoder.yudao.module.hanzhong.courserating.controller.app.vo.AppCourseRatingCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.courserating.controller.app.vo.AppCourseRatingRespVO;
import cn.iocoder.yudao.module.hanzhong.courserating.convert.CourseRatingConvert;
import cn.iocoder.yudao.module.hanzhong.courserating.dal.dataobject.CourseRatingDO;
import cn.iocoder.yudao.module.hanzhong.courserating.dal.mysql.CourseRatingMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.hanzhong.enums.ErrorCodeConstants.COURSE_NOT_EXISTS;
import static cn.iocoder.yudao.module.hanzhong.enums.ErrorCodeConstants.COURSE_NOT_PURCHASED_FOR_RATING;
import static cn.iocoder.yudao.module.hanzhong.enums.ErrorCodeConstants.COURSE_RATING_NOT_EXISTS;

/**
 * 汉中 课程评分 Service 实现类
 *
 * @author hanzhong
 */
@Service
@Validated
public class CourseRatingServiceImpl implements CourseRatingService {

    @Resource
    private CourseRatingMapper courseRatingMapper;

    @Resource
    private CourseMapper courseMapper;

    @Resource
    private CourseOrderMapper courseOrderMapper;

    @Override
    public Long createOrUpdateRating(Long userId, AppCourseRatingCreateReqVO createReqVO) {
        // 校验课程存在
        CourseDO course = courseMapper.selectById(createReqVO.getCourseId());
        if (course == null) {
            throw exception(COURSE_NOT_EXISTS);
        }
        // 付费课程需要校验是否已购买（免费课程（price=0 或 null）无需购买即可评价）
        boolean isPaid = course.getPrice() != null && course.getPrice() > 0;
        if (isPaid) {
            if (courseOrderMapper.selectActiveByUserIdAndCourseId(userId, createReqVO.getCourseId()) == null) {
                throw exception(COURSE_NOT_PURCHASED_FOR_RATING);
            }
        }
        // 查询是否已有评分记录
        CourseRatingDO existing = courseRatingMapper.selectByUserIdAndCourseId(userId, createReqVO.getCourseId());
        if (existing != null) {
            // 更新已有评分
            CourseRatingDO updateObj = new CourseRatingDO();
            updateObj.setId(existing.getId());
            updateObj.setRating(createReqVO.getRating());
            updateObj.setComment(createReqVO.getComment());
            courseRatingMapper.updateById(updateObj);
            return existing.getId();
        } else {
            // 新建评分
            CourseRatingDO ratingDO = new CourseRatingDO();
            ratingDO.setUserId(userId);
            ratingDO.setCourseId(createReqVO.getCourseId());
            ratingDO.setCourseName(course.getTitle());
            ratingDO.setRating(createReqVO.getRating());
            ratingDO.setComment(createReqVO.getComment());
            courseRatingMapper.insert(ratingDO);
            return ratingDO.getId();
        }
    }

    @Override
    public AppCourseRatingRespVO getMyRating(Long userId, Long courseId) {
        CourseRatingDO ratingDO = courseRatingMapper.selectByUserIdAndCourseId(userId, courseId);
        return ratingDO == null ? null : CourseRatingConvert.INSTANCE.convertApp(ratingDO);
    }

    @Override
    public PageResult<AppCourseRatingRespVO> getRatingPageByCourseId(PageParam pageParam, Long courseId) {
        PageResult<CourseRatingDO> pageResult = courseRatingMapper.selectPageByCourseId(pageParam, courseId);
        return CourseRatingConvert.INSTANCE.convertAppPage(pageResult);
    }

    @Override
    public double[] getAvgRatingAndCount(Long courseId) {
        Double avg = courseRatingMapper.selectAvgRatingByCourseId(courseId);
        Long count = courseRatingMapper.selectCountByCourseId(courseId);
        return new double[]{avg != null ? Math.round(avg * 10.0) / 10.0 : 0.0, count != null ? count : 0L};
    }

    @Override
    public PageResult<CourseRatingDO> getRatingAdminPage(CourseRatingPageReqVO pageReqVO) {
        return courseRatingMapper.selectAdminPage(pageReqVO);
    }

    @Override
    public void deleteRating(Long id) {
        CourseRatingDO ratingDO = courseRatingMapper.selectById(id);
        if (ratingDO == null) {
            throw exception(COURSE_RATING_NOT_EXISTS);
        }
        courseRatingMapper.deleteById(id);
    }

}
