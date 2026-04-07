package cn.iocoder.yudao.module.hanzhong.resume.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.resume.controller.admin.vo.ResumePageReqVO;
import cn.iocoder.yudao.module.hanzhong.resume.controller.app.vo.AppResumeSaveReqVO;
import cn.iocoder.yudao.module.hanzhong.resume.convert.ResumeConvert;
import cn.iocoder.yudao.module.hanzhong.resume.dal.dataobject.ResumeDO;
import cn.iocoder.yudao.module.hanzhong.resume.dal.mysql.ResumeMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional(rollbackFor = Exception.class)
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

    @Override
    public void updateResumeStatus(Long id, Integer status) {
        ResumeDO resume = resumeMapper.selectById(id);
        if (resume == null) {
            throw cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception(
                    cn.iocoder.yudao.module.hanzhong.enums.ErrorCodeConstants.RESUME_NOT_EXISTS);
        }
        ResumeDO updateObj = new ResumeDO();
        updateObj.setId(id);
        updateObj.setStatus(status);
        resumeMapper.updateById(updateObj);
    }

    @Override
    public void deleteMyResume(Long id, Long userId) {
        ResumeDO resume = resumeMapper.selectById(id);
        if (resume == null) {
            throw cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception(
                    cn.iocoder.yudao.module.hanzhong.enums.ErrorCodeConstants.RESUME_NOT_EXISTS);
        }
        // 用户只能删除自己的简历，避免越权操作
        if (!userId.equals(resume.getUserId())) {
            throw cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception(
                    cn.iocoder.yudao.module.hanzhong.enums.ErrorCodeConstants.RESUME_NOT_EXISTS);
        }
        resumeMapper.deleteById(id);
    }

    @Override
    public void deleteResume(Long id) {
        if (resumeMapper.selectById(id) == null) {
            throw cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception(
                    cn.iocoder.yudao.module.hanzhong.enums.ErrorCodeConstants.RESUME_NOT_EXISTS);
        }
        resumeMapper.deleteById(id);
    }

}
