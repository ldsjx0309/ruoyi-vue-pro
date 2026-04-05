package cn.iocoder.yudao.module.hanzhong.cardexchange.dal.dataobject;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 汉中 名片交换记录 DO
 *
 * @author hanzhong
 */
@TableName("hanzhong_card_exchange")
@KeySequence("hanzhong_card_exchange_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardExchangeDO extends BaseDO {

    /** 编号 */
    private Long id;
    /** 发起人用户编号 */
    private Long userId;
    /** 被查看名片的用户编号 */
    private Long targetUserId;
    /** 名片记录编号 */
    private Long targetCardId;
    /** 名片姓名（快照） */
    private String targetName;
    /** 名片公司（快照） */
    private String targetCompany;
    /** 交换时间 */
    private LocalDateTime exchangeTime;
    /** 备注 */
    private String remark;

}
