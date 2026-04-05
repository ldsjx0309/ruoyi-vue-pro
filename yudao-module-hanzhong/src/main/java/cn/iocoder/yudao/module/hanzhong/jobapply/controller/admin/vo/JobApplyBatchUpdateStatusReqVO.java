package cn.iocoder.yudao.module.hanzhong.jobapply.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 管理后台 - 汉中 职位申请批量更新状态请求 VO
 *
 * @author hanzhong
 */
@Schema(description = "管理后台 - 汉中 职位申请批量更新状态 Request VO")
@Data
public class JobApplyBatchUpdateStatusReqVO {

    @Schema(description = "申请编号列表", requiredMode = Schema.RequiredMode.REQUIRED, example = "[1, 2, 3]")
    @NotEmpty(message = "申请编号列表不能为空")
    private List<Long> ids;

    @Schema(description = "目标状态（0-已投递 1-已查看 2-合适 3-不合适）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "状态不能为空")
    private Integer status;

    @Schema(description = "备注（可选，主要用于不合适时填写原因）", example = "经验不足")
    private String remark;

}
