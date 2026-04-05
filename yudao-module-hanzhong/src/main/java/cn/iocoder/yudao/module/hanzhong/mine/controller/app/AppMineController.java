package cn.iocoder.yudao.module.hanzhong.mine.controller.app;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.hanzhong.card.convert.CardConvert;
import cn.iocoder.yudao.module.hanzhong.card.dal.dataobject.CardDO;
import cn.iocoder.yudao.module.hanzhong.card.service.CardService;
import cn.iocoder.yudao.module.hanzhong.cardexchange.dal.dataobject.CardExchangeDO;
import cn.iocoder.yudao.module.hanzhong.cardexchange.dal.mysql.CardExchangeMapper;
import cn.iocoder.yudao.module.hanzhong.communitypost.dal.dataobject.CommunityPostDO;
import cn.iocoder.yudao.module.hanzhong.communitypost.dal.mysql.CommunityPostMapper;
import cn.iocoder.yudao.module.hanzhong.courseorder.dal.dataobject.CourseOrderDO;
import cn.iocoder.yudao.module.hanzhong.courseorder.dal.mysql.CourseOrderMapper;
import cn.iocoder.yudao.module.hanzhong.coursefavorite.dal.dataobject.CourseFavoriteDO;
import cn.iocoder.yudao.module.hanzhong.coursefavorite.dal.mysql.CourseFavoriteMapper;
import cn.iocoder.yudao.module.hanzhong.jobapply.dal.dataobject.JobApplyDO;
import cn.iocoder.yudao.module.hanzhong.jobapply.dal.mysql.JobApplyMapper;
import cn.iocoder.yudao.module.hanzhong.message.service.MessageService;
import cn.iocoder.yudao.module.hanzhong.mine.controller.app.vo.AppMineProfileRespVO;
import cn.iocoder.yudao.module.hanzhong.mine.controller.app.vo.AppMineStatsRespVO;
import cn.iocoder.yudao.module.hanzhong.resume.convert.ResumeConvert;
import cn.iocoder.yudao.module.hanzhong.resume.dal.dataobject.ResumeDO;
import cn.iocoder.yudao.module.hanzhong.resume.service.ResumeService;
import cn.iocoder.yudao.module.hanzhong.studyrecord.dal.dataobject.StudyRecordDO;
import cn.iocoder.yudao.module.hanzhong.studyrecord.dal.mysql.StudyRecordMapper;
import cn.iocoder.yudao.module.hanzhong.userprofile.convert.UserProfileConvert;
import cn.iocoder.yudao.module.hanzhong.userprofile.dal.dataobject.UserProfileDO;
import cn.iocoder.yudao.module.hanzhong.userprofile.service.UserProfileService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

        AppMineStatsRespVO respVO = new AppMineStatsRespVO();

        // 课程订单总数
        respVO.setTotalCourseOrders(courseOrderMapper.selectCount(
                new LambdaQueryWrapper<CourseOrderDO>().eq(CourseOrderDO::getUserId, userId)));
        // 已支付（学习中）课程数
        respVO.setActiveCourses(courseOrderMapper.selectCount(
                new LambdaQueryWrapper<CourseOrderDO>()
                        .eq(CourseOrderDO::getUserId, userId)
                        .eq(CourseOrderDO::getStatus, COURSE_ORDER_STATUS_PAID)));
        // 已完成课程数（学习进度 100%）
        respVO.setCompletedCourses(studyRecordMapper.selectCount(
                new LambdaQueryWrapper<StudyRecordDO>()
                        .eq(StudyRecordDO::getUserId, userId)
                        .eq(StudyRecordDO::getStatus, STUDY_RECORD_STATUS_COMPLETED)));
        // 职位申请总数
        respVO.setTotalJobApplies(jobApplyMapper.selectCount(
                new LambdaQueryWrapper<JobApplyDO>().eq(JobApplyDO::getUserId, userId)));
        // 我的帖子总数
        respVO.setTotalPosts(communityPostMapper.selectCount(
                new LambdaQueryWrapper<CommunityPostDO>().eq(CommunityPostDO::getUserId, userId)));
        // 名片交换记录总数
        respVO.setTotalCardExchanges(cardExchangeMapper.selectCount(
                new LambdaQueryWrapper<CardExchangeDO>().eq(CardExchangeDO::getUserId, userId)));
        // 未读消息数
        respVO.setUnreadMessages(messageService.getUnreadMessageCount(userId));
        // 课程收藏总数
        respVO.setTotalFavorites(courseFavoriteMapper.selectCount(
                new LambdaQueryWrapper<CourseFavoriteDO>().eq(CourseFavoriteDO::getUserId, userId)));

        return success(respVO);
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
        respVO.setStats(stats);

        // 计算资料完整度
        respVO.setProfileCompleteness(calculateProfileCompleteness(profile, card, resume));

        return success(respVO);
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

}
