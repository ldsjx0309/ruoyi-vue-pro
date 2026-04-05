package cn.iocoder.yudao.module.hanzhong.courseorder.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 汉中 课程订单 响应 VO
 *
 * @author hanzhong
 */
@Schema(description = "管理后台 - 汉中 课程订单 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CourseOrderRespVO extends CourseOrderBaseVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    @Schema(description = "订单编号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String orderNo;

    @Schema(description = "课程名称（快照）")
    private String courseName;

    @Schema(description = "课程封面（快照）")
    private String coverUrl;

    @Schema(description = "实付价格（分）", example = "9900")
    private Integer price;

    @Schema(description = "支付时间")
    private LocalDateTime payTime;

    @Schema(description = "取消时间")
    private LocalDateTime cancelTime;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
