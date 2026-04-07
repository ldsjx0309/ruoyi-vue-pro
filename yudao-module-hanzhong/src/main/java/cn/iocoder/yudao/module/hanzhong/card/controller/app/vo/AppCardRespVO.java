package cn.iocoder.yudao.module.hanzhong.card.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户 APP - 汉中 名片 响应 VO
 *
 * @author hanzhong
 */
@Schema(description = "用户 APP - 汉中 名片 Response VO")
@Data
public class AppCardRespVO {

    @Schema(description = "编号", example = "1024")
    private Long id;

    @Schema(description = "用户编号", example = "1024")
    private Long userId;

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

    @Schema(description = "地址")
    private String address;

    @Schema(description = "标签")
    private String tags;

    @Schema(description = "分组")
    private String groupName;

    @Schema(description = "头像地址")
    private String avatarUrl;

    @Schema(description = "名片二维码地址")
    private String qrCodeUrl;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "共同好友数量（当前版本为预留字段）", example = "0")
    private Integer mutualFriendCount;

}
