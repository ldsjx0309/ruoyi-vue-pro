package cn.iocoder.yudao.module.hanzhong.hotkeyword.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.hotkeyword.controller.admin.vo.HotKeywordPageReqVO;
import cn.iocoder.yudao.module.hanzhong.hotkeyword.controller.admin.vo.HotKeywordRespVO;
import cn.iocoder.yudao.module.hanzhong.hotkeyword.controller.admin.vo.HotKeywordSaveReqVO;
import cn.iocoder.yudao.module.hanzhong.hotkeyword.convert.HotKeywordConvert;
import cn.iocoder.yudao.module.hanzhong.hotkeyword.dal.dataobject.HotKeywordDO;
import cn.iocoder.yudao.module.hanzhong.hotkeyword.dal.mysql.HotKeywordMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.hanzhong.enums.ErrorCodeConstants.HOT_KEYWORD_DUPLICATE;
import static cn.iocoder.yudao.module.hanzhong.enums.ErrorCodeConstants.HOT_KEYWORD_NOT_EXISTS;

/**
 * 汉中 热门搜索关键词 Service 实现类
 *
 * @author hanzhong
 */
@Service
@Validated
public class HotKeywordServiceImpl implements HotKeywordService {

    /** 当数据库中无启用记录时的默认关键词（兜底，避免前端展示空白） */
    private static final List<String> DEFAULT_KEYWORDS = Arrays.asList(
            "Java开发", "前端工程师", "数据分析", "产品经理",
            "Python", "Spring Boot", "Vue3", "全栈开发",
            "运营", "实习生"
    );

    @Resource
    private HotKeywordMapper hotKeywordMapper;

    @Override
    public Long createHotKeyword(HotKeywordSaveReqVO saveReqVO) {
        // 校验关键词不重复
        HotKeywordDO existing = hotKeywordMapper.selectByKeyword(saveReqVO.getKeyword());
        if (existing != null) {
            throw exception(HOT_KEYWORD_DUPLICATE);
        }
        HotKeywordDO keyword = HotKeywordConvert.INSTANCE.convert(saveReqVO);
        hotKeywordMapper.insert(keyword);
        return keyword.getId();
    }

    @Override
    public void updateHotKeyword(HotKeywordSaveReqVO saveReqVO) {
        HotKeywordDO existingById = hotKeywordMapper.selectById(saveReqVO.getId());
        if (existingById == null) {
            throw exception(HOT_KEYWORD_NOT_EXISTS);
        }
        // 若关键词内容发生变化，校验新关键词不重复
        if (!existingById.getKeyword().equals(saveReqVO.getKeyword())) {
            HotKeywordDO existingByKeyword = hotKeywordMapper.selectByKeyword(saveReqVO.getKeyword());
            if (existingByKeyword != null) {
                throw exception(HOT_KEYWORD_DUPLICATE);
            }
        }
        HotKeywordConvert.INSTANCE.update(saveReqVO, existingById);
        hotKeywordMapper.updateById(existingById);
    }

    @Override
    public void deleteHotKeyword(Long id) {
        HotKeywordDO existing = hotKeywordMapper.selectById(id);
        if (existing == null) {
            throw exception(HOT_KEYWORD_NOT_EXISTS);
        }
        hotKeywordMapper.deleteById(id);
    }

    @Override
    public PageResult<HotKeywordRespVO> getHotKeywordPage(HotKeywordPageReqVO pageReqVO) {
        PageResult<HotKeywordDO> pageResult = hotKeywordMapper.selectAdminPage(pageReqVO);
        return HotKeywordConvert.INSTANCE.convertPage(pageResult);
    }

    @Override
    public List<String> getEnabledKeywords() {
        List<HotKeywordDO> list = hotKeywordMapper.selectEnabledList();
        if (list == null || list.isEmpty()) {
            return DEFAULT_KEYWORDS;
        }
        return list.stream().map(HotKeywordDO::getKeyword).collect(Collectors.toList());
    }

}
