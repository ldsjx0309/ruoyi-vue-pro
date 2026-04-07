package cn.iocoder.yudao.module.hanzhong.userprofile.controller.admin.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 汉中 用户档案 分页 Request VO
 *
 * @author hanzhong
 */
@Schema(description = "管理后台 - 汉中 用户档案 分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserProfilePageReqVO extends PageParam {

    @Schema(description = "用户编号", example = "1024")
    private Long userId;

    @Schema(description = "用户名", example = "hanzhong_user")
    private String username;

    @Schema(description = "性别（0-未知 1-男 2-女）", example = "1")
    private Integer gender;

    @Schema(description = "语言偏好", example = "ko-KR")
    private String preferredLanguage;

    @Schema(description = "状态（0-开启 1-关闭）", example = "0")
    private Integer status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
