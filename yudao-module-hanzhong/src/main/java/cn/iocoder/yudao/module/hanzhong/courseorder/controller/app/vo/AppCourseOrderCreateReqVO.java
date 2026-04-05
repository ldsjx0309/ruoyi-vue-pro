package cn.iocoder.yudao.module.hanzhong.courseorder.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 用户 APP - 汉中 课程订单 创建 Request VO
 *
 * @author hanzhong
 */
@Schema(description = "用户 APP - 汉中 课程订单 创建 Request VO")
@Data
public class AppCourseOrderCreateReqVO {

    @Schema(description = "课程编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "课程编号不能为空")
    private Long courseId;

}
