package cn.iocoder.yudao.module.hanzhong.cardexchange.controller.admin.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 汉中 名片交换 分页 Request VO
 *
 * @author hanzhong
 */
@Schema(description = "管理后台 - 汉中 名片交换 分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CardExchangePageReqVO extends PageParam {

    @Schema(description = "发起人用户编号", example = "1024")
    private Long userId;

    @Schema(description = "被查看名片的用户编号", example = "2048")
    private Long targetUserId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
