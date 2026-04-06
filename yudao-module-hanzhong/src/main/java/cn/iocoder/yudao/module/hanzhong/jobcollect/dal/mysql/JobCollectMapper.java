package cn.iocoder.yudao.module.hanzhong.jobcollect.dal.mysql;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.hanzhong.jobcollect.dal.dataobject.JobCollectDO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 汉中 职位收藏 Mapper
 *
 * @author hanzhong
 */
@Mapper
public interface JobCollectMapper extends BaseMapperX<JobCollectDO> {

    /**
     * 根据用户ID和职位ID查询收藏记录（物理查询，不走软删除）
     */
    @Select("SELECT * FROM hanzhong_job_collect WHERE user_id = #{userId} AND job_id = #{jobId} AND deleted = 0 LIMIT 1")
    JobCollectDO selectByUserIdAndJobId(@Param("userId") Long userId, @Param("jobId") Long jobId);

    /**
     * 物理删除（用于 toggle，避免软删除 UNIQUE KEY 重复问题）
     */
    @Delete("DELETE FROM hanzhong_job_collect WHERE user_id = #{userId} AND job_id = #{jobId}")
    int physicalDeleteByUserIdAndJobId(@Param("userId") Long userId, @Param("jobId") Long jobId);

}
