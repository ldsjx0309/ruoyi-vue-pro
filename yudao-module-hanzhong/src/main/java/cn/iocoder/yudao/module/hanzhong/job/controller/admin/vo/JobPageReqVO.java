package cn.iocoder.yudao.module.hanzhong.job.controller.admin.vo;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.validation.InEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 汉中 职位 分页 Request VO
 *
 * @author hanzhong
 */
@Schema(description = "管理后台 - 汉中 职位 分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class JobPageReqVO extends PageParam {

    @Schema(description = "职位名称", example = "Java 工程师")
    private String title;

    @Schema(description = "行业", example = "互联网")
    private String industry;

    @Schema(description = "职位类型", example = "全职")
    private String jobType;

    @Schema(description = "企业类型", example = "大企业")
    private String enterpriseType;

    @Schema(description = "状态（0-开启 1-关闭）", example = "0")
    @InEnum(CommonStatusEnum.class)
    private Integer status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
