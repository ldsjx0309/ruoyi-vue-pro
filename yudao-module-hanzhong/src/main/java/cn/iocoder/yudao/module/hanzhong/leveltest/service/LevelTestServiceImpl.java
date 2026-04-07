package cn.iocoder.yudao.module.hanzhong.leveltest.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.course.controller.app.vo.AppCoursePageReqVO;
import cn.iocoder.yudao.module.hanzhong.course.controller.app.vo.AppCourseRespVO;
import cn.iocoder.yudao.module.hanzhong.course.convert.CourseConvert;
import cn.iocoder.yudao.module.hanzhong.course.dal.dataobject.CourseDO;
import cn.iocoder.yudao.module.hanzhong.course.service.CourseService;
import cn.iocoder.yudao.module.hanzhong.leveltest.controller.app.vo.AppLevelTestQuestionRespVO;
import cn.iocoder.yudao.module.hanzhong.leveltest.controller.app.vo.AppLevelTestResultRespVO;
import cn.iocoder.yudao.module.hanzhong.leveltest.controller.app.vo.AppLevelTestSubmitReqVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class LevelTestServiceImpl implements LevelTestService {

    private static final String AUDIO_BASE_PATH = "/static/hanzhong/level-test/";
    private static final Map<String, List<AppLevelTestQuestionRespVO>> QUESTION_BANK = new ConcurrentHashMap<>();
    private static final Map<Long, AppLevelTestResultRespVO> LATEST_RESULT_CACHE = new ConcurrentHashMap<>();

    static {
        register("daily");
        register("topik");
        register("business");
    }

    @Resource
    private CourseService courseService;

    @Override
    public List<AppLevelTestQuestionRespVO> getQuestions(String target) {
        return new ArrayList<>(QUESTION_BANK.getOrDefault(normalizeTarget(target), QUESTION_BANK.get("daily")));
    }

    @Override
    public AppLevelTestResultRespVO submit(Long userId, AppLevelTestSubmitReqVO reqVO) {
        List<AppLevelTestQuestionRespVO> questions = getQuestions(reqVO.getTarget());
        Map<Integer, AppLevelTestQuestionRespVO> questionMap = questions.stream()
                .collect(Collectors.toMap(AppLevelTestQuestionRespVO::getId, q -> q));
        int total = Math.min(reqVO.getQuestionIds().size(), reqVO.getAnswers().size());
        int correct = 0;
        for (int i = 0; i < total; i++) {
            AppLevelTestQuestionRespVO question = questionMap.get(reqVO.getQuestionIds().get(i));
            if (question != null && question.getAnswerIndex().equals(reqVO.getAnswers().get(i))) {
                correct++;
            }
        }
        int score = total == 0 ? 0 : (int) Math.round(correct * 100.0 / total);
        String level = score >= 85 ? "高级" : score >= 60 ? "中级" : "初级";

        AppLevelTestResultRespVO result = new AppLevelTestResultRespVO();
        result.setTarget(reqVO.getTarget());
        result.setScore(score);
        result.setRecommendedLevel(level);
        result.setRecommendedCourses(recommendCourses(reqVO.getTarget(), level));
        result.setSubmitTime(LocalDateTime.now());
        if (userId != null) {
            LATEST_RESULT_CACHE.put(userId, result);
        }
        return result;
    }

    @Override
    public AppLevelTestResultRespVO getLatestResult(Long userId) {
        return userId == null ? null : LATEST_RESULT_CACHE.get(userId);
    }

    private List<AppCourseRespVO> recommendCourses(String target, String level) {
        AppCoursePageReqVO reqVO = new AppCoursePageReqVO();
        reqVO.setPageNo(1);
        reqVO.setPageSize(3);
        reqVO.setLevel(level);
        if ("topik".equals(normalizeTarget(target))) {
            reqVO.setCategoryId(5L);
        }
        reqVO.setSortBy("hot");
        PageResult<CourseDO> page = courseService.getCoursePageForApp(reqVO);
        List<CourseDO> list = page.getList();
        if (list == null || list.isEmpty()) {
            reqVO.setLevel(null);
            list = courseService.getCoursePageForApp(reqVO).getList();
        }
        return CourseConvert.INSTANCE.convertAppList(list == null ? Collections.emptyList() : list);
    }

    private static void register(String target) {
        List<AppLevelTestQuestionRespVO> questions = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            AppLevelTestQuestionRespVO question = new AppLevelTestQuestionRespVO();
            question.setId((target.hashCode() & 0x7fffffff) % 1000 + i);
            question.setTarget(target.toUpperCase(Locale.ROOT));
            question.setQuestion(buildQuestionText(target, i));
            question.setAudioUrl(AUDIO_BASE_PATH + target + "-" + i + ".mp3");
            question.setOptions(Arrays.asList("选项 A", "选项 B", "选项 C", "选项 D"));
            question.setAnswerIndex(i % 4);
            questions.add(question);
        }
        QUESTION_BANK.put(target, questions);
    }

    private static String buildQuestionText(String target, int index) {
        if ("topik".equals(target)) {
            return "TOPIK 模拟题 " + index + "：请选择最符合语境的韩语表达。";
        }
        if ("business".equals(target)) {
            return "商务韩语题 " + index + "：请选择最合适的商务沟通表达。";
        }
        return "日常会话题 " + index + "：请选择最自然的韩语日常表达。";
    }

    private static String normalizeTarget(String target) {
        if (target == null) {
            return "daily";
        }
        String normalized = target.trim().toLowerCase(Locale.ROOT);
        if (normalized.contains("topik")) {
            return "topik";
        }
        if (normalized.contains("business") || normalized.contains("商务")) {
            return "business";
        }
        return "daily";
    }
}
