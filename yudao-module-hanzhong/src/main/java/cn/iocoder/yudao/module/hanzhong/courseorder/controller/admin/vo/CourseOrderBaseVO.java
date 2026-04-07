package cn.iocoder.yudao.module.hanzhong.courseorder.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 汉中 课程订单 Base VO
 *
 * @author hanzhong
 */
@Data
public class CourseOrderBaseVO {

    @Schema(description = "用户编号", example = "1024")
    private Long userId;

    @Schema(description = "课程编号", example = "1024")
    private Long courseId;

    @Schema(description = "支付方式", example = "wechat")
    private String paymentMethod;

    @Schema(description = "状态（0-待支付 1-已支付 2-已取消 3-已退款 4-退款申请中 5-退款拒绝）", example = "0")
    private Integer status;

    @Schema(description = "备注")
    private String remark;

}
