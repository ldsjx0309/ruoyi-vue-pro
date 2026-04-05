package cn.iocoder.yudao.module.hanzhong.resume.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.resume.controller.admin.vo.ResumePageReqVO;
import cn.iocoder.yudao.module.hanzhong.resume.controller.app.vo.AppResumeSaveReqVO;
import cn.iocoder.yudao.module.hanzhong.resume.convert.ResumeConvert;
import cn.iocoder.yudao.module.hanzhong.resume.dal.dataobject.ResumeDO;
import cn.iocoder.yudao.module.hanzhong.resume.dal.mysql.ResumeMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

/**
 * 汉中 简历 Service 实现类
 *
 * @author hanzhong
 */
@Service
@Validated
public class ResumeServiceImpl implements ResumeService {

    @Resource
    private ResumeMapper resumeMapper;

    @Override
    public ResumeDO createOrUpdateMyResume(Long userId, AppResumeSaveReqVO saveReqVO) {
        ResumeDO existing = resumeMapper.selectByUserId(userId);
        if (existing == null) {
            ResumeDO resume = ResumeConvert.INSTANCE.convert(saveReqVO);
            resume.setUserId(userId);
            resume.setStatus(0);
            resumeMapper.insert(resume);
            return resume;
        } else {
            ResumeDO updateObj = ResumeConvert.INSTANCE.convert(saveReqVO);
            updateObj.setId(existing.getId());
            resumeMapper.updateById(updateObj);
            return resumeMapper.selectById(existing.getId());
        }
    }

    @Override
    public ResumeDO getResume(Long id) {
        return resumeMapper.selectById(id);
    }

    @Override
    public ResumeDO getMyResume(Long userId) {
        return resumeMapper.selectByUserId(userId);
    }

    @Override
    public PageResult<ResumeDO> getResumePage(ResumePageReqVO pageReqVO) {
        return resumeMapper.selectPage(pageReqVO);
    }

}
