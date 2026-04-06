package cn.iocoder.yudao.module.hanzhong.jobcollect.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 管理后台 - 汉中 职位收藏 Response VO
 *
 * @author hanzhong
 */
@Schema(description = "管理后台 - 汉中 职位收藏 Response VO")
@Data
public class JobCollectRespVO {

    @Schema(description = "收藏编号", example = "1024")
    private Long id;

    @Schema(description = "用户编号", example = "1")
    private Long userId;

    @Schema(description = "职位编号", example = "1")
    private Long jobId;

    @Schema(description = "职位标题", example = "Java 后端开发工程师")
    private String jobTitle;

    @Schema(description = "公司名称", example = "汉中云创科技有限公司")
    private String company;

    @Schema(description = "收藏时间")
    private LocalDateTime createTime;

}
