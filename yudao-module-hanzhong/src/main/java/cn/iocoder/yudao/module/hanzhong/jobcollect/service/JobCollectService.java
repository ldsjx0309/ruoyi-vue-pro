package cn.iocoder.yudao.module.hanzhong.jobcollect.service;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.jobcollect.dal.dataobject.JobCollectDO;

/**
 * 汉中 职位收藏 Service 接口
 *
 * @author hanzhong
 */
public interface JobCollectService {

    /**
     * 切换职位收藏状态（收藏/取消收藏）
     *
     * @param userId   用户编号
     * @param jobId    职位编号
     * @return true 表示已收藏，false 表示已取消收藏
     */
    boolean toggleCollect(Long userId, Long jobId);

    /**
     * 判断是否已收藏
     *
     * @param userId 用户编号
     * @param jobId  职位编号
     * @return 是否已收藏
     */
    boolean isCollected(Long userId, Long jobId);

    /**
     * 获取我的职位收藏分页
     *
     * @param pageParam 分页参数
     * @param userId    用户编号
     * @return 分页结果
     */
    PageResult<JobCollectDO> getMyCollectPage(PageParam pageParam, Long userId);

    /**
     * 删除职位收藏（物理删除）
     *
     * @param id     收藏编号
     * @param userId 用户编号（用于校验归属）
     */
    void deleteCollect(Long id, Long userId);

}
