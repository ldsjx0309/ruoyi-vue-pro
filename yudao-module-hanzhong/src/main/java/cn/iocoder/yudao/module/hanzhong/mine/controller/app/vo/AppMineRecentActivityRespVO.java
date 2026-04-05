package cn.iocoder.yudao.module.hanzhong.mine.controller.app.vo;

import cn.iocoder.yudao.module.hanzhong.courseorder.controller.app.vo.AppCourseOrderRespVO;
import cn.iocoder.yudao.module.hanzhong.jobapply.controller.app.vo.AppJobApplyRespVO;
import cn.iocoder.yudao.module.hanzhong.studyrecord.controller.app.vo.AppStudyRecordRespVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 用户 APP - 汉中 我的最近动态 响应 VO
 *
 * @author hanzhong
 */
@Schema(description = "用户 APP - 汉中 我的最近动态 Response VO")
@Data
public class AppMineRecentActivityRespVO {

    @Schema(description = "最近 5 条课程订单")
    private List<AppCourseOrderRespVO> recentOrders;

    @Schema(description = "最近 5 条职位申请")
    private List<AppJobApplyRespVO> recentJobApplies;

    @Schema(description = "最近 5 条学习记录（按最后学习时间）")
    private List<AppStudyRecordRespVO> recentStudyRecords;

}
