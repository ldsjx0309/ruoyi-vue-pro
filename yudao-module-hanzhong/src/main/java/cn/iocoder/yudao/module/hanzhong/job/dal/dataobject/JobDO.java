package cn.iocoder.yudao.module.hanzhong.job.dal.dataobject;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 汉中 职位 DO
 *
 * @author hanzhong
 */
@TableName("hanzhong_job")
@KeySequence("hanzhong_job_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobDO extends BaseDO {

    /**
     * 编号
     */
    private Long id;
    /**
     * 职位名称
     */
    private String title;
    /**
     * 公司名称
     */
    private String company;
    /**
     * 薪资待遇
     */
    private String salary;
    /**
     * 工作地点
     */
    private String location;
    /**
     * 职位类别
     */
    private String category;
    /**
     * 学历要求
     */
    private String education;
    /**
     * 经验要求
     */
    private String experience;
    /**
     * 招聘人数
     */
    private Integer headcount;
    /**
     * 职位描述
     */
    private String description;
    /**
     * 联系人姓名
     */
    private String contactName;
    /**
     * 联系人电话
     */
    private String contactPhone;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 状态（0-开启 1-关闭），对应 {@link cn.iocoder.yudao.framework.common.enums.CommonStatusEnum}
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;

}
