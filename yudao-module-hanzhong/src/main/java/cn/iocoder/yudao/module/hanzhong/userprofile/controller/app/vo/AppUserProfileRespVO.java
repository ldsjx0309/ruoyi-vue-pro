package cn.iocoder.yudao.module.hanzhong.userprofile.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 用户 APP - 汉中 用户档案 响应 VO
 *
 * @author hanzhong
 */
@Schema(description = "用户 APP - 汉中 用户档案 Response VO")
@Data
public class AppUserProfileRespVO {

    @Schema(description = "编号", example = "1024")
    private Long id;

    @Schema(description = "用户编号", example = "1024")
    private Long userId;

    @Schema(description = "用户名", example = "hanzhong_user")
    private String username;

    @Schema(description = "昵称", example = "张三")
    private String nickname;

    @Schema(description = "头像地址")
    private String avatarUrl;

    @Schema(description = "性别（0-未知 1-男 2-女）", example = "1")
    private Integer gender;

    @Schema(description = "生日")
    private LocalDate birthday;

    @Schema(description = "手机号", example = "13800138000")
    private String phone;

    @Schema(description = "邮箱", example = "example@example.com")
    private String email;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "个人简介")
    private String bio;

    @Schema(description = "会员等级", example = "黄金会员")
    private String memberLevel;

    @Schema(description = "积分", example = "120")
    private Integer points;

    @Schema(description = "语言偏好", example = "ko-KR")
    private String preferredLanguage;

    @Schema(description = "通知开关", example = "true")
    private Boolean notificationEnabled;

    @Schema(description = "隐私开关", example = "true")
    private Boolean privacyEnabled;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
