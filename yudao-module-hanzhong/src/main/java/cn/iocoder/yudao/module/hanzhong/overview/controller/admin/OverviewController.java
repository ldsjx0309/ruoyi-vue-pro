package cn.iocoder.yudao.module.hanzhong.overview.controller.admin;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.hanzhong.card.dal.dataobject.CardDO;
import cn.iocoder.yudao.module.hanzhong.card.dal.mysql.CardMapper;
import cn.iocoder.yudao.module.hanzhong.cardexchange.dal.dataobject.CardExchangeDO;
import cn.iocoder.yudao.module.hanzhong.cardexchange.dal.mysql.CardExchangeMapper;
import cn.iocoder.yudao.module.hanzhong.communitypost.dal.dataobject.CommunityPostDO;
import cn.iocoder.yudao.module.hanzhong.communitypost.dal.mysql.CommunityPostMapper;
import cn.iocoder.yudao.module.hanzhong.course.dal.dataobject.CourseDO;
import cn.iocoder.yudao.module.hanzhong.course.dal.mysql.CourseMapper;
import cn.iocoder.yudao.module.hanzhong.courseorder.dal.dataobject.CourseOrderDO;
import cn.iocoder.yudao.module.hanzhong.courseorder.dal.mysql.CourseOrderMapper;
import cn.iocoder.yudao.module.hanzhong.job.dal.dataobject.JobDO;
import cn.iocoder.yudao.module.hanzhong.job.dal.mysql.JobMapper;
import cn.iocoder.yudao.module.hanzhong.jobapply.dal.dataobject.JobApplyDO;
import cn.iocoder.yudao.module.hanzhong.jobapply.dal.mysql.JobApplyMapper;
import cn.iocoder.yudao.module.hanzhong.message.dal.dataobject.MessageDO;
import cn.iocoder.yudao.module.hanzhong.message.dal.mysql.MessageMapper;
import cn.iocoder.yudao.module.hanzhong.overview.controller.admin.vo.OverviewStatsRespVO;
import cn.iocoder.yudao.module.hanzhong.overview.controller.admin.vo.UserActivityRespVO;
import cn.iocoder.yudao.module.hanzhong.studyrecord.dal.dataobject.StudyRecordDO;
import cn.iocoder.yudao.module.hanzhong.studyrecord.dal.mysql.StudyRecordMapper;
import cn.iocoder.yudao.module.hanzhong.userprofile.dal.dataobject.UserProfileDO;
import cn.iocoder.yudao.module.hanzhong.userprofile.dal.mysql.UserProfileMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 管理后台 - 汉中 概览统计
 *
 * @author hanzhong
 */
@Tag(name = "管理后台 - 汉中 概览统计")
@RestController
@RequestMapping("/hanzhong/overview")
@Validated
public class OverviewController {

    /** 课程订单状态：已支付 */
    private static final int COURSE_ORDER_STATUS_PAID = 1;
    /** 学习记录状态：已完成 */
    private static final int STUDY_RECORD_STATUS_COMPLETED = 1;

    @Resource
    private UserProfileMapper userProfileMapper;

    @Resource
    private CourseMapper courseMapper;

    @Resource
    private JobMapper jobMapper;

    @Resource
    private CourseOrderMapper courseOrderMapper;

    @Resource
    private JobApplyMapper jobApplyMapper;

    @Resource
    private CommunityPostMapper communityPostMapper;

    @Resource
    private CardMapper cardMapper;

    @Resource
    private CardExchangeMapper cardExchangeMapper;

    @Resource
    private MessageMapper messageMapper;

    @Resource
    private StudyRecordMapper studyRecordMapper;

    @GetMapping("/stats")
    @Operation(summary = "获得概览统计数据")
    @PreAuthorize("@ss.hasPermission('hanzhong:overview:query')")
    public CommonResult<OverviewStatsRespVO> getStats() {
        OverviewStatsRespVO respVO = new OverviewStatsRespVO();

        respVO.setTotalUserProfiles(userProfileMapper.selectCount(new LambdaQueryWrapper<UserProfileDO>()));
        respVO.setTotalCourses(courseMapper.selectCount(new LambdaQueryWrapper<CourseDO>()));
        respVO.setTotalJobs(jobMapper.selectCount(new LambdaQueryWrapper<JobDO>()));
        respVO.setTotalCourseOrders(courseOrderMapper.selectCount(new LambdaQueryWrapper<CourseOrderDO>()));
        respVO.setPaidCourseOrders(courseOrderMapper.selectCount(
                new LambdaQueryWrapper<CourseOrderDO>().eq(CourseOrderDO::getStatus, COURSE_ORDER_STATUS_PAID)));
        respVO.setTotalJobApplies(jobApplyMapper.selectCount(new LambdaQueryWrapper<JobApplyDO>()));
        respVO.setTotalCommunityPosts(communityPostMapper.selectCount(new LambdaQueryWrapper<CommunityPostDO>()));
        respVO.setTotalCards(cardMapper.selectCount(new LambdaQueryWrapper<CardDO>()));
        respVO.setTotalCardExchanges(cardExchangeMapper.selectCount(new LambdaQueryWrapper<CardExchangeDO>()));
        respVO.setTotalMessages(messageMapper.selectCount(new LambdaQueryWrapper<MessageDO>()));
        respVO.setUnreadMessages(messageMapper.selectCount(
                new LambdaQueryWrapper<MessageDO>().eq(MessageDO::getIsRead, Boolean.FALSE)));
        respVO.setTotalStudyRecords(studyRecordMapper.selectCount(new LambdaQueryWrapper<StudyRecordDO>()));
        respVO.setCompletedStudyRecords(studyRecordMapper.selectCount(
                new LambdaQueryWrapper<StudyRecordDO>().eq(StudyRecordDO::getStatus, STUDY_RECORD_STATUS_COMPLETED)));

        return success(respVO);
    }

    @GetMapping("/user-activity")
    @Operation(summary = "获得指定用户的活动统计数据（管理员视图）")
    @Parameter(name = "userId", description = "用户编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hanzhong:overview:query')")
    public CommonResult<UserActivityRespVO> getUserActivity(@RequestParam("userId") Long userId) {
        UserActivityRespVO respVO = new UserActivityRespVO();
        respVO.setUserId(userId);
        respVO.setTotalCourseOrders(courseOrderMapper.selectCount(
                new LambdaQueryWrapper<CourseOrderDO>().eq(CourseOrderDO::getUserId, userId)));
        respVO.setPaidCourses(courseOrderMapper.selectCount(
                new LambdaQueryWrapper<CourseOrderDO>()
                        .eq(CourseOrderDO::getUserId, userId)
                        .eq(CourseOrderDO::getStatus, COURSE_ORDER_STATUS_PAID)));
        respVO.setCompletedCourses(studyRecordMapper.selectCount(
                new LambdaQueryWrapper<StudyRecordDO>()
                        .eq(StudyRecordDO::getUserId, userId)
                        .eq(StudyRecordDO::getStatus, STUDY_RECORD_STATUS_COMPLETED)));
        respVO.setTotalJobApplies(jobApplyMapper.selectCount(
                new LambdaQueryWrapper<JobApplyDO>().eq(JobApplyDO::getUserId, userId)));
        respVO.setTotalPosts(communityPostMapper.selectCount(
                new LambdaQueryWrapper<CommunityPostDO>().eq(CommunityPostDO::getUserId, userId)));
        respVO.setTotalCardExchanges(cardExchangeMapper.selectCount(
                new LambdaQueryWrapper<CardExchangeDO>().eq(CardExchangeDO::getUserId, userId)));
        respVO.setTotalMessages(messageMapper.selectCount(
                new LambdaQueryWrapper<MessageDO>().eq(MessageDO::getUserId, userId)));
        respVO.setUnreadMessages(messageMapper.selectCount(
                new LambdaQueryWrapper<MessageDO>()
                        .eq(MessageDO::getUserId, userId)
                        .eq(MessageDO::getIsRead, Boolean.FALSE)));
        return success(respVO);
    }

}
