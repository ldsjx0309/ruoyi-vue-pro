package cn.iocoder.yudao.module.hanzhong.resume.controller.admin;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.resume.controller.admin.vo.ResumePageReqVO;
import cn.iocoder.yudao.module.hanzhong.resume.controller.admin.vo.ResumeRespVO;
import cn.iocoder.yudao.module.hanzhong.resume.convert.ResumeConvert;
import cn.iocoder.yudao.module.hanzhong.resume.dal.dataobject.ResumeDO;
import cn.iocoder.yudao.module.hanzhong.resume.service.ResumeService;
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
 * 管理后台 - 汉中 简历管理
 *
 * @author hanzhong
 */
@Tag(name = "管理后台 - 汉中 简历管理")
@RestController
@RequestMapping("/hanzhong/resume")
@Validated
public class ResumeController {

    @Resource
    private ResumeService resumeService;

    @GetMapping("/get")
    @Operation(summary = "获得简历详情")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hanzhong:resume:query')")
    public CommonResult<ResumeRespVO> getResume(@RequestParam("id") Long id) {
        ResumeDO resume = resumeService.getResume(id);
        return success(ResumeConvert.INSTANCE.convert(resume));
    }

    @GetMapping("/page")
    @Operation(summary = "获得简历分页")
    @PreAuthorize("@ss.hasPermission('hanzhong:resume:query')")
    public CommonResult<PageResult<ResumeRespVO>> getResumePage(@Valid ResumePageReqVO pageVO) {
        PageResult<ResumeDO> pageResult = resumeService.getResumePage(pageVO);
        return success(ResumeConvert.INSTANCE.convertPage(pageResult));
    }

}
