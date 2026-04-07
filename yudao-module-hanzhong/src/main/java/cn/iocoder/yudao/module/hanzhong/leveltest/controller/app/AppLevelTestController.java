package cn.iocoder.yudao.module.hanzhong.leveltest.controller.app;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.hanzhong.leveltest.controller.app.vo.AppLevelTestQuestionRespVO;
import cn.iocoder.yudao.module.hanzhong.leveltest.controller.app.vo.AppLevelTestResultRespVO;
import cn.iocoder.yudao.module.hanzhong.leveltest.controller.app.vo.AppLevelTestSubmitReqVO;
import cn.iocoder.yudao.module.hanzhong.leveltest.service.LevelTestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@RestController
@RequestMapping("/hanzhong/app/level-test")
@Tag(name = "用户 APP - 汉中 水平测试")
@Validated
public class AppLevelTestController {

    @Resource
    private LevelTestService levelTestService;

    @GetMapping("/questions")
    @Operation(summary = "获取水平测试题目")
    @PermitAll
    public CommonResult<List<AppLevelTestQuestionRespVO>> getQuestions(
            @RequestParam(value = "target", defaultValue = "daily") String target) {
        return success(levelTestService.getQuestions(target));
    }

    @PostMapping("/submit")
    @Operation(summary = "提交水平测试并返回推荐课程")
    @PermitAll
    public CommonResult<AppLevelTestResultRespVO> submit(@Valid @RequestBody AppLevelTestSubmitReqVO reqVO) {
        return success(levelTestService.submit(SecurityFrameworkUtils.getLoginUserId(), reqVO));
    }

    @GetMapping("/latest")
    @Operation(summary = "获取我最近一次测试结果")
    @org.springframework.security.access.prepost.PreAuthorize("isAuthenticated()")
    public CommonResult<AppLevelTestResultRespVO> getLatest() {
        return success(levelTestService.getLatestResult(SecurityFrameworkUtils.getLoginUserId()));
    }
}
