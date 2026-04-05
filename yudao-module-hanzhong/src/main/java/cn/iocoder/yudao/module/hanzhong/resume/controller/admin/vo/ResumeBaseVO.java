package cn.iocoder.yudao.module.hanzhong.resume.controller.admin.vo;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.validation.InEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

/**
 * 汉中 简历 Base VO
 *
 * @author hanzhong
 */
@Data
public class ResumeBaseVO {

    @Schema(description = "用户编号", example = "1024")
    private Long userId;

    @Schema(description = "求职者姓名", example = "张三")
    private String name;

    @Schema(description = "手机号", example = "13800138000")
    private String phone;

    @Schema(description = "邮箱", example = "example@example.com")
    private String email;

    @Schema(description = "性别（0-未知 1-男 2-女）", example = "1")
    private Integer gender;

    @Schema(description = "生日")
    private LocalDate birthday;

    @Schema(description = "学历", example = "本科")
    private String education;

    @Schema(description = "学校", example = "汉中大学")
    private String school;

    @Schema(description = "专业", example = "计算机科学")
    private String major;

    @Schema(description = "工作经验", example = "3年")
    private String workExperience;

    @Schema(description = "当前职位", example = "工程师")
    private String currentPosition;

    @Schema(description = "当前公司", example = "汉中科技")
    private String currentCompany;

    @Schema(description = "技能")
    private String skills;

    @Schema(description = "自我介绍")
    private String selfIntro;

    @Schema(description = "状态（0-开启 1-关闭）", example = "0")
    @InEnum(CommonStatusEnum.class)
    private Integer status;

    @Schema(description = "备注")
    private String remark;

}
