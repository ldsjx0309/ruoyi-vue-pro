package cn.iocoder.yudao.module.hanzhong.resume.dal.dataobject;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDate;

/**
 * 汉中 简历 DO
 *
 * @author hanzhong
 */
@TableName("hanzhong_resume")
@KeySequence("hanzhong_resume_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumeDO extends BaseDO {

    /** 编号 */
    private Long id;
    /** 用户编号 */
    private Long userId;
    /** 求职者姓名 */
    private String name;
    /** 手机号 */
    private String phone;
    /** 邮箱 */
    private String email;
    /** 性别（0-未知 1-男 2-女） */
    private Integer gender;
    /** 生日 */
    private LocalDate birthday;
    /** 学历 */
    private String education;
    /** 学校 */
    private String school;
    /** 专业 */
    private String major;
    /** 工作经验 */
    private String workExperience;
    /** 当前职位 */
    private String currentPosition;
    /** 当前公司 */
    private String currentCompany;
    /** 技能 */
    private String skills;
    /** 语言能力 */
    private String languageSkills;
    /** 资格证书 */
    private String certificates;
    /** 简历附件地址 */
    private String attachmentUrl;
    /** 自我介绍 */
    private String selfIntro;
    /** 状态（0-开启 1-关闭） */
    private Integer status;
    /** 备注 */
    private String remark;

}
