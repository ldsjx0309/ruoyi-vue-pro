package cn.iocoder.yudao.module.hanzhong.card.dal.dataobject;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 汉中 名片 DO
 *
 * @author hanzhong
 */
@TableName("hanzhong_card")
@KeySequence("hanzhong_card_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardDO extends BaseDO {

    /** 编号 */
    private Long id;
    /** 用户编号 */
    private Long userId;
    /** 展示名称 */
    private String name;
    /** 身份 */
    private String identity;
    /** 国别 */
    private String country;
    /** 公司 */
    private String company;
    /** 职位 */
    private String position;
    /** 手机号 */
    private String phone;
    /** 邮箱 */
    private String email;
    /** 地址 */
    private String address;
    /** 标签 */
    private String tags;
    /** 分组 */
    private String groupName;
    /** 头像地址 */
    private String avatarUrl;
    /** 名片二维码地址 */
    private String qrCodeUrl;
    /** 状态（0-开启 1-关闭） */
    private Integer status;
    /** 备注 */
    private String remark;

}
