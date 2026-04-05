package cn.iocoder.yudao.module.hanzhong.jobapply.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 汉中 职位申请 响应 VO
 *
 * @author hanzhong
 */
@Schema(description = "管理后台 - 汉中 职位申请 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class JobApplyRespVO extends JobApplyBaseVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    @Schema(description = "职位名称（快照）")
    private String jobTitle;

    @Schema(description = "公司（快照）")
    private String company;

    @Schema(description = "申请时间")
    private LocalDateTime applyTime;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
