package cn.iocoder.yudao.module.hanzhong.banner.dal.mysql;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.hanzhong.banner.controller.admin.vo.BannerPageReqVO;
import cn.iocoder.yudao.module.hanzhong.banner.dal.dataobject.BannerDO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 汉中 Banner Mapper
 *
 * @author hanzhong
 */
@Mapper
public interface BannerMapper extends BaseMapperX<BannerDO> {

    default PageResult<BannerDO> selectPage(BannerPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BannerDO>()
                .likeIfPresent(BannerDO::getTitle, reqVO.getTitle())
                .eqIfPresent(BannerDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(BannerDO::getCreateTime, reqVO.getCreateTime())
                .orderByAsc(BannerDO::getSort));
    }

    /**
     * 查询当前有效的 Banner 列表（状态开启且在有效期内）
     */
    default List<BannerDO> selectListByActive() {
        LocalDateTime now = LocalDateTime.now();
        return selectList(new LambdaQueryWrapperX<BannerDO>()
                .eq(BannerDO::getStatus, CommonStatusEnum.ENABLE.getStatus())
                .and(w -> w.isNull(BannerDO::getStartTime).or().le(BannerDO::getStartTime, now))
                .and(w -> w.isNull(BannerDO::getEndTime).or().ge(BannerDO::getEndTime, now))
                .orderByAsc(BannerDO::getSort));
    }

}
