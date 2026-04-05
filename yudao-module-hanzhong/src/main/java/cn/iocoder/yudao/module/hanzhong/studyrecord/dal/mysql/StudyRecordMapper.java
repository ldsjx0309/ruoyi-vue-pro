package cn.iocoder.yudao.module.hanzhong.studyrecord.dal.mysql;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.hanzhong.studyrecord.controller.admin.vo.StudyRecordPageReqVO;
import cn.iocoder.yudao.module.hanzhong.studyrecord.controller.app.vo.AppStudyRecordPageReqVO;
import cn.iocoder.yudao.module.hanzhong.studyrecord.dal.dataobject.StudyRecordDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 汉中 学习记录 Mapper
 *
 * @author hanzhong
 */
@Mapper
public interface StudyRecordMapper extends BaseMapperX<StudyRecordDO> {

    default PageResult<StudyRecordDO> selectPage(StudyRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<StudyRecordDO>()
                .eqIfPresent(StudyRecordDO::getUserId, reqVO.getUserId())
                .eqIfPresent(StudyRecordDO::getCourseId, reqVO.getCourseId())
                .eqIfPresent(StudyRecordDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(StudyRecordDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(StudyRecordDO::getCreateTime));
    }

    default PageResult<StudyRecordDO> selectPageByUserId(AppStudyRecordPageReqVO reqVO, Long userId) {
        return selectPage(reqVO, new LambdaQueryWrapperX<StudyRecordDO>()
                .eq(StudyRecordDO::getUserId, userId)
                .orderByDesc(StudyRecordDO::getCreateTime));
    }

    default StudyRecordDO selectByUserIdAndCourseId(Long userId, Long courseId) {
        return selectOne(new LambdaQueryWrapperX<StudyRecordDO>()
                .eq(StudyRecordDO::getUserId, userId)
                .eq(StudyRecordDO::getCourseId, courseId));
    }

}
