package cn.iocoder.yudao.module.hanzhong.coursecategory.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.coursecategory.controller.admin.vo.CourseCategoryCreateReqVO;
import cn.iocoder.yudao.module.hanzhong.coursecategory.controller.admin.vo.CourseCategoryPageReqVO;
import cn.iocoder.yudao.module.hanzhong.coursecategory.controller.admin.vo.CourseCategoryUpdateReqVO;
import cn.iocoder.yudao.module.hanzhong.coursecategory.dal.dataobject.CourseCategoryDO;

import java.util.List;

/**
 * 汉中 课程分类 Service 接口
 *
 * @author hanzhong
 */
public interface CourseCategoryService {

    /**
     * 创建课程分类
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCourseCategory(CourseCategoryCreateReqVO createReqVO);

    /**
     * 更新课程分类
     *
     * @param updateReqVO 更新信息
     */
    void updateCourseCategory(CourseCategoryUpdateReqVO updateReqVO);

    /**
     * 更新课程分类状态
     *
     * @param id     课程分类编号
     * @param status 状态
     */
    void updateCourseCategoryStatus(Long id, Integer status);

    /**
     * 删除课程分类
     *
     * @param id 编号
     */
    void deleteCourseCategory(Long id);

    /**
     * 获得课程分类
     *
     * @param id 编号
     * @return 课程分类
     */
    CourseCategoryDO getCourseCategory(Long id);

    /**
     * 获得课程分类分页
     *
     * @param pageReqVO 分页查询
     * @return 课程分类分页
     */
    PageResult<CourseCategoryDO> getCourseCategoryPage(CourseCategoryPageReqVO pageReqVO);

    /**
     * 获得启用的课程分类列表（App 使用）
     *
     * @return 启用的课程分类列表
     */
    List<CourseCategoryDO> getEnabledCourseCategoryList();

}
