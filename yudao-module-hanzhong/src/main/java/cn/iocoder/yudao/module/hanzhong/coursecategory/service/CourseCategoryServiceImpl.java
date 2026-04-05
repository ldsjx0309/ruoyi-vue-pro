package cn.iocoder.yudao.module.hanzhong.coursecategory.service;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.coursecategory.controller.admin.vo.CourseCategoryCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.coursecategory.controller.admin.vo.CourseCategoryPageReqVO;
import cn.iocoder.yudao.module.hanzhong.coursecategory.controller.admin.vo.CourseCategoryUpdateReqVO;
import cn.iocoder.yudao.module.hanzhong.coursecategory.convert.CourseCategoryConvert;
import cn.iocoder.yudao.module.hanzhong.coursecategory.dal.dataobject.CourseCategoryDO;
import cn.iocoder.yudao.module.hanzhong.coursecategory.dal.mysql.CourseCategoryMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.hanzhong.enums.ErrorCodeConstants.COURSE_CATEGORY_NOT_EXISTS;

/**
 * 汉中 课程分类 Service 实现类
 *
 * @author hanzhong
 */
@Service
@Validated
public class CourseCategoryServiceImpl implements CourseCategoryService {

    @Resource
    private CourseCategoryMapper courseCategoryMapper;

    @Override
    public Long createCourseCategory(CourseCategoryCreateReqVO createReqVO) {
        CourseCategoryDO courseCategory = CourseCategoryConvert.INSTANCE.convert(createReqVO);
        if (courseCategory.getParentId() == null) {
            courseCategory.setParentId(0L);
        }
        courseCategoryMapper.insert(courseCategory);
        return courseCategory.getId();
    }

    @Override
    public void updateCourseCategory(CourseCategoryUpdateReqVO updateReqVO) {
        validateCourseCategoryExists(updateReqVO.getId());
        CourseCategoryDO updateObj = CourseCategoryConvert.INSTANCE.convert(updateReqVO);
        courseCategoryMapper.updateById(updateObj);
    }

    @Override
    public void updateCourseCategoryStatus(Long id, Integer status) {
        validateCourseCategoryExists(id);
        CourseCategoryDO updateObj = new CourseCategoryDO();
        updateObj.setId(id);
        updateObj.setStatus(status);
        courseCategoryMapper.updateById(updateObj);
    }

    @Override
    public void deleteCourseCategory(Long id) {
        validateCourseCategoryExists(id);
        courseCategoryMapper.deleteById(id);
    }

    private void validateCourseCategoryExists(Long id) {
        if (courseCategoryMapper.selectById(id) == null) {
            throw exception(COURSE_CATEGORY_NOT_EXISTS);
        }
    }

    @Override
    public CourseCategoryDO getCourseCategory(Long id) {
        return courseCategoryMapper.selectById(id);
    }

    @Override
    public PageResult<CourseCategoryDO> getCourseCategoryPage(CourseCategoryPageReqVO pageReqVO) {
        return courseCategoryMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CourseCategoryDO> getEnabledCourseCategoryList() {
        return courseCategoryMapper.selectListByStatus(CommonStatusEnum.ENABLE.getStatus());
    }

}
