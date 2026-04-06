package cn.iocoder.yudao.module.hanzhong.hotkeyword.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.hotkeyword.controller.admin.vo.HotKeywordPageReqVO;
import cn.iocoder.yudao.module.hanzhong.hotkeyword.controller.admin.vo.HotKeywordRespVO;
import cn.iocoder.yudao.module.hanzhong.hotkeyword.controller.admin.vo.HotKeywordSaveReqVO;

import java.util.List;

/**
 * 汉中 热门搜索关键词 Service 接口
 *
 * @author hanzhong
 */
public interface HotKeywordService {

    /**
     * 创建热门关键词
     *
     * @param saveReqVO 请求 VO
     * @return 编号
     */
    Long createHotKeyword(HotKeywordSaveReqVO saveReqVO);

    /**
     * 更新热门关键词
     *
     * @param saveReqVO 请求 VO
     */
    void updateHotKeyword(HotKeywordSaveReqVO saveReqVO);

    /**
     * 删除热门关键词
     *
     * @param id 编号
     */
    void deleteHotKeyword(Long id);

    /**
     * 获得热门关键词（管理后台分页）
     */
    PageResult<HotKeywordRespVO> getHotKeywordPage(HotKeywordPageReqVO pageReqVO);

    /**
     * 获得已启用的热门关键词列表（App 端搜索页使用）
     *
     * @return 关键词字符串列表，按 sort 升序
     */
    List<String> getEnabledKeywords();

}
