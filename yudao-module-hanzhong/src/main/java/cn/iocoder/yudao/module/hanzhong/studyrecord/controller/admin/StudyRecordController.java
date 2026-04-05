package cn.iocoder.yudao.module.hanzhong.studyrecord.controller.admin;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.studyrecord.controller.admin.vo.StudyRecordPageReqVO;
import cn.iocoder.yudao.module.hanzhong.studyrecord.controller.admin.vo.StudyRecordRespVO;
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
 * 管理后台 - 汉中 学习记录管理
 *
 * @author hanzhong
 */
@Tag(name = "管理后台 - 汉中 学习记录管理")
@RestController
@RequestMapping("/hanzhong/study-record")
@Validated
public class StudyRecordController {

    @Resource
    private StudyRecordService studyRecordService;

    @GetMapping("/get")
    @Operation(summary = "获得学习记录详情")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hanzhong:study-record:query')")
    public CommonResult<StudyRecordRespVO> getStudyRecord(@RequestParam("id") Long id) {
        StudyRecordDO record = studyRecordService.getStudyRecord(id);
        return success(StudyRecordConvert.INSTANCE.convert(record));
    }

    @GetMapping("/page")
    @Operation(summary = "获得学习记录分页")
    @PreAuthorize("@ss.hasPermission('hanzhong:study-record:query')")
    public CommonResult<PageResult<StudyRecordRespVO>> getStudyRecordPage(@Valid StudyRecordPageReqVO pageVO) {
        PageResult<StudyRecordDO> pageResult = studyRecordService.getStudyRecordPage(pageVO);
        return success(StudyRecordConvert.INSTANCE.convertPage(pageResult));
    }

}
