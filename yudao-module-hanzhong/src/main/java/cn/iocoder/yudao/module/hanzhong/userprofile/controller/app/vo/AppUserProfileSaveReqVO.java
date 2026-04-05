package cn.iocoder.yudao.module.hanzhong.userprofile.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

/**
 * 用户 APP - 汉中 用户档案 创建或更新 Request VO
 *
 * @author hanzhong
 */
@Schema(description = "用户 APP - 汉中 用户档案 创建或更新 Request VO")
@Data
public class AppUserProfileSaveReqVO {

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

}
