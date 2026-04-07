package cn.iocoder.yudao.module.hanzhong.banner.controller.admin.vo;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.validation.InEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 汉中 Banner Base VO，供创建、修改、详情子 VO 使用
 *
 * @author hanzhong
 */
@Data
public class BannerBaseVO {

    @Schema(description = "标题", requiredMode = Schema.RequiredMode.REQUIRED, example = "首页活动 Banner")
    @NotNull(message = "标题不能为空")
    private String title;

    @Schema(description = "图片地址", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "图片地址不能为空")
    private String picUrl;

    @Schema(description = "跳转类型（0-不跳转 1-跳转课程 2-跳转职位 3-自定义链接 4-外链）", example = "0")
    @Min(value = 0, message = "跳转类型最小值为 0")
    @Max(value = 4, message = "跳转类型最大值为 4")
    private Integer linkType;

    @Schema(description = "跳转链接", example = "https://example.com")
    private String linkUrl;

    @Schema(description = "跳转业务 id", example = "1024")
    private Long linkId;

    @Schema(description = "排序", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "排序不能为空")
    private Integer sort;

    @Schema(description = "状态（0-开启 1-关闭）", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    @NotNull(message = "状态不能为空")
    @InEnum(CommonStatusEnum.class)
    private Integer status;

    @Schema(description = "生效开始时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime startTime;

    @Schema(description = "生效结束时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime endTime;

    @Schema(description = "备注")
    private String remark;

}
