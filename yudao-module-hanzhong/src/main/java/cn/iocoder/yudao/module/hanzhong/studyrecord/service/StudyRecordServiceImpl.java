package cn.iocoder.yudao.module.hanzhong.studyrecord.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.course.dal.dataobject.CourseDO;
import cn.iocoder.yudao.module.hanzhong.course.dal.mysql.CourseMapper;
import cn.iocoder.yudao.module.hanzhong.studyrecord.controller.admin.vo.StudyRecordPageReqVO;
import cn.iocoder.yudao.module.hanzhong.studyrecord.controller.app.vo.AppStudyRecordPageReqVO;
import cn.iocoder.yudao.module.hanzhong.studyrecord.controller.app.vo.AppStudyRecordUpdateProgressReqVO;
import cn.iocoder.yudao.module.hanzhong.studyrecord.dal.dataobject.StudyRecordDO;
import cn.iocoder.yudao.module.hanzhong.studyrecord.dal.mysql.StudyRecordMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDateTime;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProgress(Long userId, AppStudyRecordUpdateProgressReqVO reqVO) {
        StudyRecordDO existing = studyRecordMapper.selectByUserIdAndCourseId(userId, reqVO.getCourseId());
        LocalDateTime now = LocalDateTime.now();
        if (existing == null) {
            CourseDO course = courseMapper.selectById(reqVO.getCourseId());
            StudyRecordDO record = new StudyRecordDO();
            record.setUserId(userId);
            record.setCourseId(reqVO.getCourseId());
            record.setCourseName(course != null ? course.getTitle() : null);
            record.setProgress(reqVO.getProgress());
            record.setLastStudyTime(now);
            if (reqVO.getProgress() >= 100) {
                record.setStatus(1);
                record.setFinishTime(now);
            } else {
                record.setStatus(0);
            }
            studyRecordMapper.insert(record);
        } else {
            StudyRecordDO updateObj = new StudyRecordDO();
            updateObj.setId(existing.getId());
            updateObj.setProgress(reqVO.getProgress());
            updateObj.setLastStudyTime(now);
            if (reqVO.getProgress() >= 100) {
                updateObj.setStatus(1);
                updateObj.setFinishTime(now);
            } else {
                updateObj.setStatus(0);
            }
            studyRecordMapper.updateById(updateObj);
        }
    }

    @Override
    public StudyRecordDO getStudyRecord(Long id) {
        return studyRecordMapper.selectById(id);
    }

    @Override
    public PageResult<StudyRecordDO> getStudyRecordPage(StudyRecordPageReqVO pageReqVO) {
        return studyRecordMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<StudyRecordDO> getMyStudyRecordPage(AppStudyRecordPageReqVO pageReqVO, Long userId) {
        return studyRecordMapper.selectPageByUserId(pageReqVO, userId);
    }

}
