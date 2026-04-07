package cn.iocoder.yudao.module.hanzhong.mine.controller.app;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.hanzhong.card.convert.CardConvert;
import cn.iocoder.yudao.module.hanzhong.card.dal.dataobject.CardDO;
import cn.iocoder.yudao.module.hanzhong.card.service.CardService;
import cn.iocoder.yudao.module.hanzhong.cardexchange.dal.dataobject.CardExchangeDO;
import cn.iocoder.yudao.module.hanzhong.cardexchange.dal.mysql.CardExchangeMapper;
import cn.iocoder.yudao.module.hanzhong.communitypost.controller.app.vo.AppCommunityPostRespVO;
import cn.iocoder.yudao.module.hanzhong.communitypost.convert.CommunityPostConvert;
import cn.iocoder.yudao.module.hanzhong.communitypost.dal.dataobject.CommunityPostDO;
import cn.iocoder.yudao.module.hanzhong.communitypost.dal.mysql.CommunityPostMapper;
import cn.iocoder.yudao.module.hanzhong.courseorder.convert.CourseOrderConvert;
import cn.iocoder.yudao.module.hanzhong.courseorder.controller.app.vo.AppCourseOrderRespVO;
import cn.iocoder.yudao.module.hanzhong.courseorder.dal.dataobject.CourseOrderDO;
import cn.iocoder.yudao.module.hanzhong.courseorder.dal.mysql.CourseOrderMapper;
import cn.iocoder.yudao.module.hanzhong.coursefavorite.controller.app.vo.AppCourseFavoriteRespVO;
import cn.iocoder.yudao.module.hanzhong.coursefavorite.convert.CourseFavoriteConvert;
import cn.iocoder.yudao.module.hanzhong.coursefavorite.dal.dataobject.CourseFavoriteDO;
import cn.iocoder.yudao.module.hanzhong.coursefavorite.dal.mysql.CourseFavoriteMapper;
import cn.iocoder.yudao.module.hanzhong.jobapply.convert.JobApplyConvert;
import cn.iocoder.yudao.module.hanzhong.jobapply.controller.app.vo.AppJobApplyRespVO;
import cn.iocoder.yudao.module.hanzhong.jobapply.dal.dataobject.JobApplyDO;
import cn.iocoder.yudao.module.hanzhong.jobapply.dal.mysql.JobApplyMapper;
import cn.iocoder.yudao.module.hanzhong.jobcollect.controller.app.vo.AppJobCollectRespVO;
import cn.iocoder.yudao.module.hanzhong.jobcollect.dal.dataobject.JobCollectDO;
import cn.iocoder.yudao.module.hanzhong.jobcollect.dal.mysql.JobCollectMapper;
import cn.iocoder.yudao.module.hanzhong.message.service.MessageService;
import cn.iocoder.yudao.module.hanzhong.mine.controller.app.vo.AppMineNotificationSummaryRespVO;
import cn.iocoder.yudao.module.hanzhong.mine.controller.app.vo.AppMineProfileRespVO;
import cn.iocoder.yudao.module.hanzhong.mine.controller.app.vo.AppMineRecentActivityRespVO;
import cn.iocoder.yudao.module.hanzhong.mine.controller.app.vo.AppMineStatsRespVO;
import cn.iocoder.yudao.module.hanzhong.resume.convert.ResumeConvert;
import cn.iocoder.yudao.module.hanzhong.resume.dal.dataobject.ResumeDO;
import cn.iocoder.yudao.module.hanzhong.resume.service.ResumeService;
import cn.iocoder.yudao.module.hanzhong.studyrecord.convert.StudyRecordConvert;
import cn.iocoder.yudao.module.hanzhong.studyrecord.controller.app.vo.AppStudyRecordRespVO;
import cn.iocoder.yudao.module.hanzhong.studyrecord.dal.dataobject.StudyRecordDO;
import cn.iocoder.yudao.module.hanzhong.studyrecord.dal.mysql.StudyRecordMapper;
import cn.iocoder.yudao.module.hanzhong.userprofile.convert.UserProfileConvert;
import cn.iocoder.yudao.module.hanzhong.userprofile.dal.dataobject.UserProfileDO;
import cn.iocoder.yudao.module.hanzhong.userprofile.service.UserProfileService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 用户 APP - 汉中 我的统计
 *
 * @author hanzhong
 */
@RestController
@RequestMapping("/hanzhong/app/mine")
@Tag(name = "用户 APP - 汉中 我的")
@Validated
public class AppMineController {

    /** 课程订单状态：已支付 */
    private static final int COURSE_ORDER_STATUS_PAID = 1;
    /** 学习记录状态：已完成 */
    private static final int STUDY_RECORD_STATUS_COMPLETED = 1;
    /** 职位申请状态：已投递（待 HR 审核，尚未更新进展） */
    private static final int JOB_APPLY_STATUS_SUBMITTED = 0;

    @Resource
    private CourseOrderMapper courseOrderMapper;

    @Resource
    private JobApplyMapper jobApplyMapper;

    @Resource
    private CommunityPostMapper communityPostMapper;

    @Resource
    private CardExchangeMapper cardExchangeMapper;

    @Resource
    private StudyRecordMapper studyRecordMapper;

    @Resource
    private CourseFavoriteMapper courseFavoriteMapper;

    @Resource
    private JobCollectMapper jobCollectMapper;

    @Resource
    private MessageService messageService;

    @Resource
    private UserProfileService userProfileService;

    @Resource
    private CardService cardService;

    @Resource
    private ResumeService resumeService;

    @GetMapping("/stats")
    @Operation(summary = "获取我的统计数据（我的页面入口）")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<AppMineStatsRespVO> getMyStats() {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        return success(buildMyStats(userId));
    }

    @GetMapping("/profile")
    @Operation(summary = "获取我的主页信息（用户档案 + 名片 + 简历 + 统计数据聚合）")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<AppMineProfileRespVO> getMyProfile() {
        Long userId = SecurityFrameworkUtils.getLoginUserId();

        AppMineProfileRespVO respVO = new AppMineProfileRespVO();

        // 用户档案
        UserProfileDO profile = userProfileService.getMyProfile(userId);
        respVO.setProfile(UserProfileConvert.INSTANCE.convertApp(profile));

        // 我的名片
        CardDO card = cardService.getMyCard(userId);
        respVO.setCard(CardConvert.INSTANCE.convertApp(card));

        // 我的简历
        ResumeDO resume = resumeService.getMyResume(userId);
        respVO.setResume(ResumeConvert.INSTANCE.convertApp(resume));

        // 统计数据
        respVO.setStats(buildMyStats(userId));

        // 计算资料完整度
        respVO.setProfileCompleteness(calculateProfileCompleteness(profile, card, resume));

        return success(respVO);
    }

    /**
     * 构建指定用户的统计数据（供 getMyStats 和 getMyProfile 复用）
     */
    private AppMineStatsRespVO buildMyStats(Long userId) {
        AppMineStatsRespVO stats = new AppMineStatsRespVO();
        stats.setTotalCourseOrders(courseOrderMapper.selectCount(
                new LambdaQueryWrapper<CourseOrderDO>().eq(CourseOrderDO::getUserId, userId)));
        stats.setActiveCourses(courseOrderMapper.selectCount(
                new LambdaQueryWrapper<CourseOrderDO>()
                        .eq(CourseOrderDO::getUserId, userId)
                        .eq(CourseOrderDO::getStatus, COURSE_ORDER_STATUS_PAID)));
        stats.setCompletedCourses(studyRecordMapper.selectCount(
                new LambdaQueryWrapper<StudyRecordDO>()
                        .eq(StudyRecordDO::getUserId, userId)
                        .eq(StudyRecordDO::getStatus, STUDY_RECORD_STATUS_COMPLETED)));
        stats.setTotalJobApplies(jobApplyMapper.selectCount(
                new LambdaQueryWrapper<JobApplyDO>().eq(JobApplyDO::getUserId, userId)));
        stats.setTotalPosts(communityPostMapper.selectCount(
                new LambdaQueryWrapper<CommunityPostDO>().eq(CommunityPostDO::getUserId, userId)));
        stats.setTotalCardExchanges(cardExchangeMapper.selectCount(
                new LambdaQueryWrapper<CardExchangeDO>().eq(CardExchangeDO::getUserId, userId)));
        stats.setUnreadMessages(messageService.getUnreadMessageCount(userId));
        stats.setTotalFavorites(courseFavoriteMapper.selectCount(
                new LambdaQueryWrapper<CourseFavoriteDO>().eq(CourseFavoriteDO::getUserId, userId)));
        stats.setTotalJobCollects(jobCollectMapper.selectCount(
                new LambdaQueryWrapper<JobCollectDO>().eq(JobCollectDO::getUserId, userId)));
        return stats;
    }

    /**
     * 计算资料完整度（简单规则：检查关键字段是否填写）
     * 分为三段：用户档案（4分）、名片（3分）、简历（3分），总分10分，转为百分比
     */
    private int calculateProfileCompleteness(UserProfileDO profile, CardDO card, ResumeDO resume) {
        int score = 0;
        // 用户档案关键字段
        if (profile != null) {
            if (profile.getNickname() != null && !profile.getNickname().isEmpty()) score++;
            if (profile.getAvatarUrl() != null && !profile.getAvatarUrl().isEmpty()) score++;
            if (profile.getPhone() != null && !profile.getPhone().isEmpty()) score++;
            if (profile.getBio() != null && !profile.getBio().isEmpty()) score++;
        }
        // 名片关键字段
        if (card != null) {
            if (card.getCompany() != null && !card.getCompany().isEmpty()) score++;
            if (card.getPosition() != null && !card.getPosition().isEmpty()) score++;
            if (card.getPhone() != null && !card.getPhone().isEmpty()) score++;
        }
        // 简历关键字段
        if (resume != null) {
            if (resume.getEducation() != null && !resume.getEducation().isEmpty()) score++;
            if (resume.getSkills() != null && !resume.getSkills().isEmpty()) score++;
            if (resume.getWorkExperience() != null && !resume.getWorkExperience().isEmpty()) score++;
        }
        return score * 10;
    }

    @GetMapping("/recent-activity")
    @Operation(summary = "获取最近动态（最近 5 条订单、申请、学习记录）")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<AppMineRecentActivityRespVO> getRecentActivity() {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        AppMineRecentActivityRespVO respVO = new AppMineRecentActivityRespVO();

        // 最近 5 条课程订单（使用分页方式，page=1, size=5）
        Page<CourseOrderDO> orderPage = courseOrderMapper.selectPage(
                new Page<>(1, 5),
                new LambdaQueryWrapper<CourseOrderDO>()
                        .eq(CourseOrderDO::getUserId, userId)
                        .orderByDesc(CourseOrderDO::getCreateTime));
        respVO.setRecentOrders(orderPage.getRecords().stream()
                .map(CourseOrderConvert.INSTANCE::convertApp)
                .collect(Collectors.toList()));

        // 最近 5 条职位申请
        Page<JobApplyDO> applyPage = jobApplyMapper.selectPage(
                new Page<>(1, 5),
                new LambdaQueryWrapper<JobApplyDO>()
                        .eq(JobApplyDO::getUserId, userId)
                        .orderByDesc(JobApplyDO::getCreateTime));
        respVO.setRecentJobApplies(applyPage.getRecords().stream()
                .map(JobApplyConvert.INSTANCE::convertApp)
                .collect(Collectors.toList()));

        // 最近 5 条学习记录（按最后学习时间降序）
        Page<StudyRecordDO> studyPage = studyRecordMapper.selectPage(
                new Page<>(1, 5),
                new LambdaQueryWrapper<StudyRecordDO>()
                        .eq(StudyRecordDO::getUserId, userId)
                        .orderByDesc(StudyRecordDO::getLastStudyTime));
        respVO.setRecentStudyRecords(studyPage.getRecords().stream()
                .map(StudyRecordConvert.INSTANCE::convertApp)
                .collect(Collectors.toList()));

        return success(respVO);
    }

    @GetMapping("/my-posts")
    @Operation(summary = "获取我发布的社区帖子（分页）")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<PageResult<AppCommunityPostRespVO>> getMyPosts(@Valid PageParam pageParam) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        Page<CommunityPostDO> page = communityPostMapper.selectPage(
                new Page<>(pageParam.getPageNo(), pageParam.getPageSize()),
                new LambdaQueryWrapper<CommunityPostDO>()
                        .eq(CommunityPostDO::getUserId, userId)
                        .orderByDesc(CommunityPostDO::getCreateTime));
        PageResult<AppCommunityPostRespVO> result = new PageResult<>(
                CommunityPostConvert.INSTANCE.convertAppList(page.getRecords()),
                page.getTotal());
        return success(result);
    }

    @GetMapping("/my-study-records")
    @Operation(summary = "获取我的学习记录分页（按最后学习时间降序）")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<PageResult<AppStudyRecordRespVO>> getMyStudyRecords(@Valid PageParam pageParam) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        Page<StudyRecordDO> page = studyRecordMapper.selectPage(
                new Page<>(pageParam.getPageNo(), pageParam.getPageSize()),
                new LambdaQueryWrapper<StudyRecordDO>()
                        .eq(StudyRecordDO::getUserId, userId)
                        .orderByDesc(StudyRecordDO::getLastStudyTime));
        PageResult<AppStudyRecordRespVO> result = new PageResult<>(
                page.getRecords().stream()
                        .map(StudyRecordConvert.INSTANCE::convertApp)
                        .collect(Collectors.toList()),
                page.getTotal());
        return success(result);
    }

    @GetMapping("/my-orders")
    @Operation(summary = "获取我的课程订单分页（按创建时间降序）")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<PageResult<AppCourseOrderRespVO>> getMyOrders(@Valid PageParam pageParam) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        Page<CourseOrderDO> page = courseOrderMapper.selectPage(
                new Page<>(pageParam.getPageNo(), pageParam.getPageSize()),
                new LambdaQueryWrapper<CourseOrderDO>()
                        .eq(CourseOrderDO::getUserId, userId)
                        .orderByDesc(CourseOrderDO::getCreateTime));
        PageResult<AppCourseOrderRespVO> result = new PageResult<>(
                page.getRecords().stream()
                        .map(CourseOrderConvert.INSTANCE::convertApp)
                        .collect(Collectors.toList()),
                page.getTotal());
        return success(result);
    }

    @GetMapping("/my-job-applies")
    @Operation(summary = "获取我的职位申请分页（按申请时间降序）")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<PageResult<AppJobApplyRespVO>> getMyJobApplies(@Valid PageParam pageParam) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        Page<JobApplyDO> page = jobApplyMapper.selectPage(
                new Page<>(pageParam.getPageNo(), pageParam.getPageSize()),
                new LambdaQueryWrapper<JobApplyDO>()
                        .eq(JobApplyDO::getUserId, userId)
                        .orderByDesc(JobApplyDO::getApplyTime));
        PageResult<AppJobApplyRespVO> result = new PageResult<>(
                page.getRecords().stream()
                        .map(JobApplyConvert.INSTANCE::convertApp)
                        .collect(Collectors.toList()),
                page.getTotal());
        return success(result);
    }

    @GetMapping("/notification-summary")
    @Operation(summary = "获取通知概要（未读消息数 + 有进展的职位申请数，用于小程序角标）")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<AppMineNotificationSummaryRespVO> getNotificationSummary() {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        AppMineNotificationSummaryRespVO respVO = new AppMineNotificationSummaryRespVO();

        // 未读消息数
        long unreadMessages = messageService.getUnreadMessageCount(userId);
        respVO.setUnreadMessages(unreadMessages);

        // 有新进展的职位申请数（状态非 0=已投递 的申请，即已被 HR 处理的申请）
        long updatedJobApplies = jobApplyMapper.selectCount(
                new LambdaQueryWrapper<JobApplyDO>()
                        .eq(JobApplyDO::getUserId, userId)
                        .ne(JobApplyDO::getStatus, JOB_APPLY_STATUS_SUBMITTED));
        respVO.setUpdatedJobApplies(updatedJobApplies);

        // 当前版本退款结果通知统一为 0（通知已通过消息系统推送）
        respVO.setProcessedRefunds(0L);

        respVO.setTotalNotifications(unreadMessages + updatedJobApplies);
        return success(respVO);
    }

    @GetMapping("/my-course-favorites")
    @Operation(summary = "获取我的课程收藏分页（mine 页快捷入口）")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<PageResult<AppCourseFavoriteRespVO>> getMyCourseFavorites(@Valid PageParam pageParam) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        PageResult<CourseFavoriteDO> pageResult = courseFavoriteMapper.selectPageByUserId(pageParam, userId);
        return success(CourseFavoriteConvert.INSTANCE.convertAppPage(pageResult));
    }

    @GetMapping("/my-job-collects")
    @Operation(summary = "获取我的职位收藏分页（mine 页快捷入口）")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<PageResult<AppJobCollectRespVO>> getMyJobCollects(@Valid PageParam pageParam) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        Page<JobCollectDO> page = jobCollectMapper.selectPage(
                new Page<>(pageParam.getPageNo(), pageParam.getPageSize()),
                new LambdaQueryWrapper<JobCollectDO>()
                        .eq(JobCollectDO::getUserId, userId)
                        .orderByDesc(JobCollectDO::getCreateTime));
        java.util.List<AppJobCollectRespVO> list = page.getRecords().stream().map(d -> {
            AppJobCollectRespVO vo = new AppJobCollectRespVO();
            vo.setId(d.getId());
            vo.setJobId(d.getJobId());
            vo.setJobTitle(d.getJobTitle());
            vo.setCompany(d.getCompany());
            vo.setCreateTime(d.getCreateTime());
            return vo;
        }).collect(Collectors.toList());
        return success(new PageResult<>(list, page.getTotal()));
    }

}
