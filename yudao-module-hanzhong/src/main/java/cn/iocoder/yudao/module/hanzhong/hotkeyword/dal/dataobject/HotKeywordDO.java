package cn.iocoder.yudao.module.hanzhong.hotkeyword.dal.dataobject;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 汉中 热门搜索关键词 DO
 *
 * @author hanzhong
 */
@TableName("hanzhong_hot_keyword")
@KeySequence("hanzhong_hot_keyword_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotKeywordDO extends BaseDO {

    /** 编号 */
    private Long id;
    /** 关键词 */
    private String keyword;
    /** 排序（升序，数字越小越靠前） */
    private Integer sort;
    /** 状态（0-禁用 1-启用） */
    private Integer status;

}
