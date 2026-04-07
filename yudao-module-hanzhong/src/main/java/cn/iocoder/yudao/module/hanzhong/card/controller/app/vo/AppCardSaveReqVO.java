package cn.iocoder.yudao.module.hanzhong.card.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户 APP - 汉中 名片 创建或更新 Request VO
 *
 * @author hanzhong
 */
@Schema(description = "用户 APP - 汉中 名片 创建或更新 Request VO")
@Data
public class AppCardSaveReqVO {

    @Schema(description = "展示名称", example = "张三")
    private String name;

    @Schema(description = "身份", example = "商务顾问")
    private String identity;

    @Schema(description = "国别", example = "韩国")
    private String country;

    @Schema(description = "公司", example = "汉中科技")
    private String company;

    @Schema(description = "职位", example = "工程师")
    private String position;

    @Schema(description = "手机号", example = "13800138000")
    private String phone;

    @Schema(description = "邮箱", example = "example@example.com")
    private String email;

    @Schema(description = "地址", example = "首尔市江南区")
    private String address;

    @Schema(description = "标签，多个以英文逗号分隔", example = "教育,商务,留学")
    private String tags;

    @Schema(description = "分组名称", example = "重点人脉")
    private String groupName;

    @Schema(description = "头像地址")
    private String avatarUrl;

    @Schema(description = "名片二维码地址")
    private String qrCodeUrl;

}
