package cn.iocoder.yudao.module.hanzhong.coursecategory.dal.mysql;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.hanzhong.coursecategory.controller.admin.vo.CourseCategoryPageReqVO;
import cn.iocoder.yudao.module.hanzhong.coursecategory.dal.dataobject.CourseCategoryDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 汉中 课程分类 Mapper
 *
 * @author hanzhong
 */
@Mapper
public interface CourseCategoryMapper extends BaseMapperX<CourseCategoryDO> {

    default PageResult<CourseCategoryDO> selectPage(CourseCategoryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CourseCategoryDO>()
                .likeIfPresent(CourseCategoryDO::getName, reqVO.getName())
                .eqIfPresent(CourseCategoryDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(CourseCategoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByAsc(CourseCategoryDO::getSort));
    }

    default List<CourseCategoryDO> selectListByStatus(Integer status) {
        return selectList(new LambdaQueryWrapperX<CourseCategoryDO>()
                .eq(CourseCategoryDO::getStatus, status)
                .orderByAsc(CourseCategoryDO::getSort));
    }

}
