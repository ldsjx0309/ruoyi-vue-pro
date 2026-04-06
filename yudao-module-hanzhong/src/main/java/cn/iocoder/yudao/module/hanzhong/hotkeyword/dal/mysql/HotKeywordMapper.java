package cn.iocoder.yudao.module.hanzhong.hotkeyword.dal.mysql;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.hanzhong.hotkeyword.controller.admin.vo.HotKeywordPageReqVO;
import cn.iocoder.yudao.module.hanzhong.hotkeyword.dal.dataobject.HotKeywordDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 汉中 热门搜索关键词 Mapper
 *
 * @author hanzhong
 */
@Mapper
public interface HotKeywordMapper extends BaseMapperX<HotKeywordDO> {

    /**
     * 查询已启用的热门关键词列表（按 sort 升序，供 App 端搜索页展示）
     */
    default List<HotKeywordDO> selectEnabledList() {
        return selectList(new LambdaQueryWrapper<HotKeywordDO>()
                .eq(HotKeywordDO::getStatus, 1)
                .orderByAsc(HotKeywordDO::getSort));
    }

    default PageResult<HotKeywordDO> selectAdminPage(HotKeywordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapper<HotKeywordDO>()
                .like(reqVO.getKeyword() != null && !reqVO.getKeyword().isEmpty(),
                        HotKeywordDO::getKeyword, reqVO.getKeyword())
                .eq(reqVO.getStatus() != null, HotKeywordDO::getStatus, reqVO.getStatus())
                .orderByAsc(HotKeywordDO::getSort));
    }

    default HotKeywordDO selectByKeyword(String keyword) {
        return selectOne(new LambdaQueryWrapper<HotKeywordDO>()
                .eq(HotKeywordDO::getKeyword, keyword)
                .last("LIMIT 1"));
    }

}
