package cn.iocoder.yudao.module.hanzhong.resume.dal.mysql;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.hanzhong.resume.controller.admin.vo.ResumePageReqVO;
import cn.iocoder.yudao.module.hanzhong.resume.dal.dataobject.ResumeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 汉中 简历 Mapper
 *
 * @author hanzhong
 */
@Mapper
public interface ResumeMapper extends BaseMapperX<ResumeDO> {

    default PageResult<ResumeDO> selectPage(ResumePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ResumeDO>()
                .eqIfPresent(ResumeDO::getUserId, reqVO.getUserId())
                .eqIfPresent(ResumeDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ResumeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ResumeDO::getCreateTime));
    }

    default ResumeDO selectByUserId(Long userId) {
        return selectOne(ResumeDO::getUserId, userId);
    }

}
