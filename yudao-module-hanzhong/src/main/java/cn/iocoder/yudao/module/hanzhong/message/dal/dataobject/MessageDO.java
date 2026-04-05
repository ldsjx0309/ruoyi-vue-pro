package cn.iocoder.yudao.module.hanzhong.message.dal.dataobject;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 汉中 消息 DO
 *
 * @author hanzhong
 */
@TableName("hanzhong_message")
@KeySequence("hanzhong_message_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDO extends BaseDO {

    /**
     * 编号
     */
    private Long id;
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 消息类型（0-系统消息 1-通知 2-活动）
     */
    private Integer type;
    /**
     * 是否已读
     */
    private Boolean isRead;
    /**
     * 备注
     */
    private String remark;

}
