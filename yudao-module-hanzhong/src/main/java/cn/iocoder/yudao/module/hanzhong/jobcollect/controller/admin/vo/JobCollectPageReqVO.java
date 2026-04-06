package cn.iocoder.yudao.module.hanzhong.jobcollect.controller.admin.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 管理后台 - 汉中 职位收藏分页 Request VO
 *
 * @author hanzhong
 */
@Schema(description = "管理后台 - 汉中 职位收藏分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class JobCollectPageReqVO extends PageParam {

    @Schema(description = "用户编号", example = "1024")
    private Long userId;

    @Schema(description = "职位编号", example = "1")
    private Long jobId;

}
