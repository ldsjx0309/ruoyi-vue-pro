package cn.iocoder.yudao.module.hanzhong.courseorder.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 管理后台 - 汉中 课程订单 状态更新 Request VO
 *
 * @author hanzhong
 */
@Schema(description = "管理后台 - 汉中 课程订单 状态更新 Request VO")
@Data
public class CourseOrderUpdateStatusReqVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "编号不能为空")
    private Long id;

    @Schema(description = "状态（0-待支付 1-已支付 2-已取消 3-已退款）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "状态不能为空")
    private Integer status;

}
