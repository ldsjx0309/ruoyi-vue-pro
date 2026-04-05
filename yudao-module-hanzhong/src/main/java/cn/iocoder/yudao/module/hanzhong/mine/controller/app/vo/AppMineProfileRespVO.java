package cn.iocoder.yudao.module.hanzhong.mine.controller.app.vo;

import cn.iocoder.yudao.module.hanzhong.card.controller.app.vo.AppCardRespVO;
import cn.iocoder.yudao.module.hanzhong.resume.controller.app.vo.AppResumeRespVO;
import cn.iocoder.yudao.module.hanzhong.userprofile.controller.app.vo.AppUserProfileRespVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户 APP - 汉中 我的主页 响应 VO（用户档案 + 名片 + 简历聚合）
 *
 * @author hanzhong
 */
@Schema(description = "用户 APP - 汉中 我的主页 Response VO")
@Data
public class AppMineProfileRespVO {

    @Schema(description = "用户档案（可为 null，表示尚未创建）")
    private AppUserProfileRespVO profile;

    @Schema(description = "我的名片（可为 null，表示尚未创建）")
    private AppCardRespVO card;

    @Schema(description = "我的简历（可为 null，表示尚未创建）")
    private AppResumeRespVO resume;

    @Schema(description = "统计数据")
    private AppMineStatsRespVO stats;

}
