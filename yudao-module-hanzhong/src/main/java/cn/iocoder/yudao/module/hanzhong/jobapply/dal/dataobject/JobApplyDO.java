package cn.iocoder.yudao.module.hanzhong.jobapply.dal.dataobject;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 汉中 职位申请 DO
 *
 * @author hanzhong
 */
@TableName("hanzhong_job_apply")
@KeySequence("hanzhong_job_apply_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobApplyDO extends BaseDO {

    /** 编号 */
    private Long id;
    /** 用户编号 */
    private Long userId;
    /** 职位编号 */
    private Long jobId;
    /** 职位名称（快照） */
    private String jobTitle;
    /** 公司（快照） */
    private String company;
    /** 使用的简历编号 */
    private Long resumeId;
    /** 状态（0-已投递 1-查看简历 2-邀请面试 3-不合适 4-已录用） */
    private Integer status;
    /** 申请时间 */
    private LocalDateTime applyTime;
    /** 备注 */
    private String remark;

}
