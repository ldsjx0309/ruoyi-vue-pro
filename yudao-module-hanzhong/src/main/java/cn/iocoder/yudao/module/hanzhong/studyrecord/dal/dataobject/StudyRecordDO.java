package cn.iocoder.yudao.module.hanzhong.studyrecord.dal.dataobject;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 汉中 学习记录 DO
 *
 * @author hanzhong
 */
@TableName("hanzhong_study_record")
@KeySequence("hanzhong_study_record_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudyRecordDO extends BaseDO {

    /** 编号 */
    private Long id;
    /** 用户编号 */
    private Long userId;
    /** 课程编号 */
    private Long courseId;
    /** 课程名称（快照） */
    private String courseName;
    /** 课程封面（快照） */
    private String coverUrl;
    /** 最后学习的章节编号（用于视频学习页断点续播） */
    private Long lastSectionId;
    /** 学习进度（0-100） */
    private Integer progress;
    /** 最后学习时间 */
    private LocalDateTime lastStudyTime;
    /** 完成时间 */
    private LocalDateTime finishTime;
    /** 状态（0-学习中 1-已完成） */
    private Integer status;
    /** 备注 */
    private String remark;

}
