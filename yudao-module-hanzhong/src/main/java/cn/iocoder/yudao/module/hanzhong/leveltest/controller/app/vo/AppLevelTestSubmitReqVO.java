package cn.iocoder.yudao.module.hanzhong.leveltest.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Schema(description = "用户 APP - 汉中 水平测试提交 Request VO")
@Data
public class AppLevelTestSubmitReqVO {

    @Schema(description = "测试目标", requiredMode = Schema.RequiredMode.REQUIRED, example = "TOPIK")
    @NotNull(message = "测试目标不能为空")
    private String target;

    @Schema(description = "题目编号列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "题目不能为空")
    private List<Integer> questionIds;

    @Schema(description = "答案索引列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "答案不能为空")
    private List<Integer> answers;
}
