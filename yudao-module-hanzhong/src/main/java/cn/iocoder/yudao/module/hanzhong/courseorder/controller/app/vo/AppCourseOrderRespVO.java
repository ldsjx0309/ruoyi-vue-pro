package cn.iocoder.yudao.module.hanzhong.courseorder.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户 APP - 汉中 课程订单 响应 VO
 *
 * @author hanzhong
 */
@Schema(description = "用户 APP - 汉中 课程订单 Response VO")
@Data
public class AppCourseOrderRespVO {

    @Schema(description = "编号", example = "1024")
    private Long id;

    @Schema(description = "订单编号")
    private String orderNo;

    @Schema(description = "课程编号", example = "1024")
    private Long courseId;

    @Schema(description = "课程名称（快照）")
    private String courseName;

    @Schema(description = "课程封面（快照）")
    private String coverUrl;

    @Schema(description = "实付价格（分）", example = "9900")
    private Integer price;

    @Schema(description = "状态（0-待支付 1-已支付 2-已取消 3-已退款）", example = "0")
    private Integer status;

    @Schema(description = "支付时间")
    private LocalDateTime payTime;

    @Schema(description = "取消时间")
    private LocalDateTime cancelTime;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
