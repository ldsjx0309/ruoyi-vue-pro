package cn.iocoder.yudao.module.hanzhong.studyrecord.controller.app;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.hanzhong.studyrecord.controller.app.vo.AppStudyRecordPageReqVO;
import cn.iocoder.yudao.module.hanzhong.studyrecord.controller.app.vo.AppStudyRecordRespVO;
import cn.iocoder.yudao.module.hanzhong.studyrecord.controller.app.vo.AppStudyRecordUpdateProgressReqVO;
import cn.iocoder.yudao.module.hanzhong.studyrecord.convert.StudyRecordConvert;
import cn.iocoder.yudao.module.hanzhong.studyrecord.dal.dataobject.StudyRecordDO;
import cn.iocoder.yudao.module.hanzhong.studyrecord.service.StudyRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 用户 APP - 汉中 学习记录
 *
 * @author hanzhong
 */
@RestController
@RequestMapping("/hanzhong/app/study-record")
@Tag(name = "用户 APP - 汉中 学习记录")
@Validated
public class AppStudyRecordController {

    @Resource
    private StudyRecordService studyRecordService;

    @GetMapping("/page")
    @Operation(summary = "获取我的学习记录分页")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<PageResult<AppStudyRecordRespVO>> getMyStudyRecordPage(@Valid AppStudyRecordPageReqVO pageReqVO) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        PageResult<StudyRecordDO> pageResult = studyRecordService.getMyStudyRecordPage(pageReqVO, userId);
        return success(StudyRecordConvert.INSTANCE.convertAppPage(pageResult));
    }

    @GetMapping("/get")
    @Operation(summary = "获取学习记录详情")
    @Parameter(name = "id", description = "记录编号", required = true, example = "1024")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<AppStudyRecordRespVO> getStudyRecord(@RequestParam("id") Long id) {
        StudyRecordDO record = studyRecordService.getStudyRecord(id);
        return success(StudyRecordConvert.INSTANCE.convertApp(record));
    }

    @PostMapping("/update-progress")
    @Operation(summary = "更新学习进度")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Boolean> updateProgress(@Valid @RequestBody AppStudyRecordUpdateProgressReqVO reqVO) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        studyRecordService.updateProgress(userId, reqVO);
        return success(true);
    }

    @GetMapping("/get-by-course")
    @Operation(summary = "根据课程编号获取学习记录")
    @Parameter(name = "courseId", description = "课程编号", required = true, example = "1024")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<AppStudyRecordRespVO> getStudyRecordByCourse(@RequestParam("courseId") Long courseId) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        StudyRecordDO record = studyRecordService.getStudyRecordByUserIdAndCourseId(userId, courseId);
        return success(StudyRecordConvert.INSTANCE.convertApp(record));
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除我的学习记录（用户主动清除）")
    @Parameter(name = "id", description = "记录编号", required = true, example = "1024")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<Boolean> deleteStudyRecord(@RequestParam("id") Long id) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        studyRecordService.deleteMyStudyRecord(id, userId);
        return success(true);
    }

}
