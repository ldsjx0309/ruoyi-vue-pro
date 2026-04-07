package cn.iocoder.yudao.module.hanzhong.resume.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.resume.controller.admin.vo.ResumePageReqVO;
import cn.iocoder.yudao.module.hanzhong.resume.controller.app.vo.AppResumeSaveReqVO;
import cn.iocoder.yudao.module.hanzhong.resume.dal.dataobject.ResumeDO;

/**
 * 汉中 简历 Service 接口
 *
 * @author hanzhong
 */
public interface ResumeService {

    /**
     * 创建或更新我的简历
     */
    ResumeDO createOrUpdateMyResume(Long userId, AppResumeSaveReqVO saveReqVO);

    /**
     * 获得简历
     */
    ResumeDO getResume(Long id);

    /**
     * 获得我的简历
     */
    ResumeDO getMyResume(Long userId);

    /**
     * 获得简历分页
     */
    PageResult<ResumeDO> getResumePage(ResumePageReqVO pageReqVO);

    /**
     * 更新简历状态（管理员）
     */
    void updateResumeStatus(Long id, Integer status);

    /**
     * 删除我的简历（用户操作，仅可删除自己的简历）
     *
     * @param id     简历编号
     * @param userId 当前登录用户编号
     */
    void deleteMyResume(Long id, Long userId);

    /**
     * 删除简历（管理员操作）
     *
     * @param id 简历编号
     */
    void deleteResume(Long id);

}
