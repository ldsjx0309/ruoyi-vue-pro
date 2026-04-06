package cn.iocoder.yudao.module.hanzhong.coursesection.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.coursesection.controller.admin.vo.CourseSectionCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.coursesection.controller.admin.vo.CourseSectionPageReqVO;
import cn.iocoder.yudao.module.hanzhong.coursesection.controller.admin.vo.CourseSectionUpdateReqVO;
import cn.iocoder.yudao.module.hanzhong.coursesection.dal.dataobject.CourseSectionDO;

import java.util.List;

/**
 * 汉中 课程章节 Service 接口
 *
 * @author hanzhong
 */
public interface CourseSectionService {

    Long createSection(CourseSectionCreateReqVO createReqVO);

    void updateSection(CourseSectionUpdateReqVO updateReqVO);

    void updateSectionStatus(Long id, Integer status);

    void deleteSection(Long id);

    CourseSectionDO getSection(Long id);

    List<CourseSectionDO> getSectionsByCourseId(Long courseId);

    /**
     * 获取课程的所有章节（含禁用），供管理后台使用
     */
    List<CourseSectionDO> getSectionsByCourseIdForAdmin(Long courseId);

    PageResult<CourseSectionDO> getSectionPage(CourseSectionPageReqVO pageReqVO);

}
