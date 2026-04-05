package cn.iocoder.yudao.module.hanzhong.userprofile.dal.dataobject;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDate;

/**
 * 汉中 用户档案 DO
 *
 * @author hanzhong
 */
@TableName("hanzhong_user_profile")
@KeySequence("hanzhong_user_profile_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDO extends BaseDO {

    /** 编号 */
    private Long id;
    /** 用户编号 */
    private Long userId;
    /** 昵称 */
    private String nickname;
    /** 头像地址 */
    private String avatarUrl;
    /** 性别（0-未知 1-男 2-女） */
    private Integer gender;
    /** 生日 */
    private LocalDate birthday;
    /** 手机号 */
    private String phone;
    /** 邮箱 */
    private String email;
    /** 地址 */
    private String address;
    /** 个人简介 */
    private String bio;
    /** 状态（0-开启 1-关闭） */
    private Integer status;

}
