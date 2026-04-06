package cn.iocoder.yudao.module.hanzhong.coursefavorite.service;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.course.dal.dataobject.CourseDO;
import cn.iocoder.yudao.module.hanzhong.course.dal.mysql.CourseMapper;
import cn.iocoder.yudao.module.hanzhong.coursefavorite.dal.dataobject.CourseFavoriteDO;
import cn.iocoder.yudao.module.hanzhong.coursefavorite.dal.mysql.CourseFavoriteMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.hanzhong.enums.ErrorCodeConstants.COURSE_NOT_EXISTS;

/**
 * 汉中 课程收藏 Service 实现类
 *
 * @author hanzhong
 */
@Service
@Validated
public class CourseFavoriteServiceImpl implements CourseFavoriteService {

    @Resource
    private CourseFavoriteMapper courseFavoriteMapper;

    @Resource
    private CourseMapper courseMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean toggleFavorite(Long userId, Long courseId) {
        CourseDO course = courseMapper.selectById(courseId);
        if (course == null) {
            throw exception(COURSE_NOT_EXISTS);
        }
        CourseFavoriteDO existing = courseFavoriteMapper.selectByUserIdAndCourseId(userId, courseId);
        if (existing != null) {
            // 已收藏 -> 取消收藏（物理删除，避免唯一键冲突）
            courseFavoriteMapper.deleteByUserIdAndCourseId(userId, courseId);
            return false;
        } else {
            // 未收藏 -> 收藏
            CourseFavoriteDO favorite = new CourseFavoriteDO();
            favorite.setUserId(userId);
            favorite.setCourseId(courseId);
            favorite.setCourseName(course.getTitle());
            favorite.setCoverUrl(course.getCoverUrl());
            courseFavoriteMapper.insert(favorite);
            return true;
        }
    }

    @Override
    public boolean isFavorited(Long userId, Long courseId) {
        return courseFavoriteMapper.selectByUserIdAndCourseId(userId, courseId) != null;
    }

    @Override
    public PageResult<CourseFavoriteDO> getMyFavoritePage(PageParam pageParam, Long userId) {
        return courseFavoriteMapper.selectPageByUserId(pageParam, userId);
    }

}
