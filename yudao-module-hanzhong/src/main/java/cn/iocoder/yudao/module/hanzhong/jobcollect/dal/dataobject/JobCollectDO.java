package cn.iocoder.yudao.module.hanzhong.jobcollect.dal.dataobject;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 汉中 职位收藏 DO
 *
 * @author hanzhong
 */
@TableName("hanzhong_job_collect")
@KeySequence("hanzhong_job_collect_seq")
@Data
@EqualsAndHashCode(callSuper = true)
public class JobCollectDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 职位编号
     */
    private Long jobId;

    /**
     * 职位标题（冗余）
     */
    private String jobTitle;

    /**
     * 公司名称（冗余）
     */
    private String company;

}
