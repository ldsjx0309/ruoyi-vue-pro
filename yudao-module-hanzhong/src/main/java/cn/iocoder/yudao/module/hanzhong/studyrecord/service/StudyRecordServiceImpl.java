package cn.iocoder.yudao.module.hanzhong.studyrecord.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.course.dal.dataobject.CourseDO;
import cn.iocoder.yudao.module.hanzhong.course.dal.mysql.CourseMapper;
import cn.iocoder.yudao.module.hanzhong.message.service.MessageService;
import cn.iocoder.yudao.module.hanzhong.studyrecord.controller.admin.vo.StudyRecordPageReqVO;
import cn.iocoder.yudao.module.hanzhong.studyrecord.controller.app.vo.AppStudyRecordPageReqVO;
import cn.iocoder.yudao.module.hanzhong.studyrecord.controller.app.vo.AppStudyRecordUpdateProgressReqVO;
import cn.iocoder.yudao.module.hanzhong.studyrecord.dal.dataobject.StudyRecordDO;
import cn.iocoder.yudao.module.hanzhong.studyrecord.dal.mysql.StudyRecordMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.hanzhong.enums.ErrorCodeConstants.STUDY_RECORD_NOT_EXISTS;

/**
 * 汉中 学习记录 Service 实现类
 *
 * @author hanzhong
 */
@Service
@Validated
public class StudyRecordServiceImpl implements StudyRecordService {

    @Resource
    private StudyRecordMapper studyRecordMapper;

    @Resource
    private CourseMapper courseMapper;

    @Resource
    @Lazy
    private MessageService messageService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProgress(Long userId, AppStudyRecordUpdateProgressReqVO reqVO) {
        StudyRecordDO existing = studyRecordMapper.selectByUserIdAndCourseId(userId, reqVO.getCourseId());
        LocalDateTime now = LocalDateTime.now();
        boolean justCompleted = false;
        String courseName = null;
        if (existing == null) {
            CourseDO course = courseMapper.selectById(reqVO.getCourseId());
            courseName = course != null ? course.getTitle() : null;
            StudyRecordDO record = new StudyRecordDO();
            record.setUserId(userId);
            record.setCourseId(reqVO.getCourseId());
            record.setCourseName(courseName);
            record.setProgress(reqVO.getProgress());
            record.setLastStudyTime(now);
            record.setLastSectionId(reqVO.getSectionId());
            if (reqVO.getProgress() >= 100) {
                record.setStatus(1);
                record.setFinishTime(now);
                justCompleted = true;
            } else {
                record.setStatus(0);
            }
            studyRecordMapper.insert(record);
        } else {
            courseName = existing.getCourseName();
            StudyRecordDO updateObj = new StudyRecordDO();
            updateObj.setId(existing.getId());
            updateObj.setProgress(reqVO.getProgress());
            updateObj.setLastStudyTime(now);
            if (reqVO.getSectionId() != null) {
                updateObj.setLastSectionId(reqVO.getSectionId());
            }
            if (reqVO.getProgress() >= 100) {
                updateObj.setStatus(1);
                updateObj.setFinishTime(now);
                // 仅在之前未完成时发送完成通知
                justCompleted = existing.getStatus() != null && existing.getStatus() != 1;
            } else {
                updateObj.setStatus(0);
            }
            studyRecordMapper.updateById(updateObj);
        }
        // 完成课程后发送通知
        if (justCompleted) {
            try {
                messageService.sendSystemMessage(userId, "课程学习完成",
                        "恭喜您完成课程《" + (courseName != null ? courseName : "") + "》的学习，继续保持！");
            } catch (Exception ignored) {
                // 消息发送失败不影响主流程
            }
        }
    }

    @Override
    public StudyRecordDO getStudyRecord(Long id) {
        return studyRecordMapper.selectById(id);
    }

    @Override
    public StudyRecordDO getStudyRecordByUserIdAndCourseId(Long userId, Long courseId) {
        return studyRecordMapper.selectByUserIdAndCourseId(userId, courseId);
    }

    @Override
    public PageResult<StudyRecordDO> getStudyRecordPage(StudyRecordPageReqVO pageReqVO) {
        return studyRecordMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<StudyRecordDO> getMyStudyRecordPage(AppStudyRecordPageReqVO pageReqVO, Long userId) {
        return studyRecordMapper.selectPageByUserId(pageReqVO, userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMyStudyRecord(Long id, Long userId) {
        StudyRecordDO record = studyRecordMapper.selectById(id);
        if (record == null) {
            throw exception(STUDY_RECORD_NOT_EXISTS);
        }
        // 故意使用相同的"不存在"错误码，避免向调用方泄露其他用户的学习记录是否存在（安全防泄露）
        if (!userId.equals(record.getUserId())) {
            throw exception(STUDY_RECORD_NOT_EXISTS);
        }
        studyRecordMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void adminResetStudyRecord(Long id) {
        StudyRecordDO record = studyRecordMapper.selectById(id);
        if (record == null) {
            throw exception(STUDY_RECORD_NOT_EXISTS);
        }
        StudyRecordDO updateObj = new StudyRecordDO();
        updateObj.setId(id);
        updateObj.setProgress(0);
        updateObj.setStatus(0);
        updateObj.setLastSectionId(null);
        updateObj.setFinishTime(null);
        updateObj.setLastStudyTime(null);
        studyRecordMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void adminDeleteStudyRecord(Long id) {
        if (studyRecordMapper.selectById(id) == null) {
            throw exception(STUDY_RECORD_NOT_EXISTS);
        }
        studyRecordMapper.deleteById(id);
    }

}
