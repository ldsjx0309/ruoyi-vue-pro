package cn.iocoder.yudao.module.hanzhong.coursesection.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.coursesection.controller.admin.vo.CourseSectionCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.coursesection.controller.admin.vo.CourseSectionPageReqVO;
import cn.iocoder.yudao.module.hanzhong.coursesection.controller.admin.vo.CourseSectionUpdateReqVO;
import cn.iocoder.yudao.module.hanzhong.coursesection.convert.CourseSectionConvert;
import cn.iocoder.yudao.module.hanzhong.coursesection.dal.dataobject.CourseSectionDO;
import cn.iocoder.yudao.module.hanzhong.coursesection.dal.mysql.CourseSectionMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.hanzhong.enums.ErrorCodeConstants.COURSE_SECTION_NOT_EXISTS;

/**
 * 汉中 课程章节 Service 实现类
 *
 * @author hanzhong
 */
@Service
@Validated
public class CourseSectionServiceImpl implements CourseSectionService {

    @Resource
    private CourseSectionMapper courseSectionMapper;

    @Override
    public Long createSection(CourseSectionCreateReqVO createReqVO) {
        CourseSectionDO section = CourseSectionConvert.INSTANCE.convert(createReqVO);
        if (section.getSort() == null) {
            section.setSort(0);
        }
        if (section.getFreePreview() == null) {
            section.setFreePreview(0);
        }
        if (section.getStatus() == null) {
            section.setStatus(0);
        }
        courseSectionMapper.insert(section);
        return section.getId();
    }

    @Override
    public void updateSection(CourseSectionUpdateReqVO updateReqVO) {
        validateSectionExists(updateReqVO.getId());
        CourseSectionDO updateObj = CourseSectionConvert.INSTANCE.convert(updateReqVO);
        courseSectionMapper.updateById(updateObj);
    }

    @Override
    public void updateSectionStatus(Long id, Integer status) {
        validateSectionExists(id);
        CourseSectionDO updateObj = new CourseSectionDO();
        updateObj.setId(id);
        updateObj.setStatus(status);
        courseSectionMapper.updateById(updateObj);
    }

    @Override
    public void deleteSection(Long id) {
        validateSectionExists(id);
        courseSectionMapper.deleteById(id);
    }

    @Override
    public CourseSectionDO getSection(Long id) {
        return courseSectionMapper.selectById(id);
    }

    @Override
    public List<CourseSectionDO> getSectionsByCourseId(Long courseId) {
        return courseSectionMapper.selectByCourseId(courseId);
    }

    @Override
    public List<CourseSectionDO> getSectionsByCourseIdForAdmin(Long courseId) {
        return courseSectionMapper.selectByCourseIdAll(courseId);
    }

    @Override
    public PageResult<CourseSectionDO> getSectionPage(CourseSectionPageReqVO pageReqVO) {
        return courseSectionMapper.selectPage(pageReqVO);
    }

    private void validateSectionExists(Long id) {
        if (courseSectionMapper.selectById(id) == null) {
            throw exception(COURSE_SECTION_NOT_EXISTS);
        }
    }

}
