package cn.iocoder.yudao.module.hanzhong.courseorder.controller.app.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 用户 APP - 汉中 课程订单 分页 Request VO
 *
 * @author hanzhong
 */
@Schema(description = "用户 APP - 汉中 课程订单 分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AppCourseOrderPageReqVO extends PageParam {

    @Schema(description = "状态（0-待支付 1-已支付 2-已取消 3-已退款）", example = "0")
    private Integer status;

}
