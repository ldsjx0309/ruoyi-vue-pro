package cn.iocoder.yudao.module.hanzhong.studyrecord.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.studyrecord.controller.admin.vo.StudyRecordPageReqVO;
import cn.iocoder.yudao.module.hanzhong.studyrecord.controller.app.vo.AppStudyRecordPageReqVO;
import cn.iocoder.yudao.module.hanzhong.studyrecord.controller.app.vo.AppStudyRecordUpdateProgressReqVO;
import cn.iocoder.yudao.module.hanzhong.studyrecord.dal.dataobject.StudyRecordDO;

/**
 * 汉中 学习记录 Service 接口
 *
 * @author hanzhong
 */
public interface StudyRecordService {

    /**
     * 更新学习进度（不存在则创建）
     */
    void updateProgress(Long userId, AppStudyRecordUpdateProgressReqVO reqVO);

    /**
     * 获得学习记录
     */
    StudyRecordDO getStudyRecord(Long id);

    /**
     * 获得用户对某课程的学习记录
     */
    StudyRecordDO getStudyRecordByUserIdAndCourseId(Long userId, Long courseId);

    /**
     * 获得学习记录分页（管理员）
     */
    PageResult<StudyRecordDO> getStudyRecordPage(StudyRecordPageReqVO pageReqVO);

    /**
     * 获得我的学习记录分页
     */
    PageResult<StudyRecordDO> getMyStudyRecordPage(AppStudyRecordPageReqVO pageReqVO, Long userId);

    /**
     * 删除我的学习记录（用户主动移除）
     */
    void deleteMyStudyRecord(Long id, Long userId);

    /**
     * 管理员重置学习记录（将进度重置为 0，状态重置为学习中）
     */
    void adminResetStudyRecord(Long id);

    /**
     * 管理员删除学习记录
     */
    void adminDeleteStudyRecord(Long id);

}
