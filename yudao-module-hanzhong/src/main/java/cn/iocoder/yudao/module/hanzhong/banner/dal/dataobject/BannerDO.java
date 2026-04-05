package cn.iocoder.yudao.module.hanzhong.banner.dal.dataobject;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 汉中 Banner DO
 *
 * @author hanzhong
 */
@TableName("hanzhong_banner")
@KeySequence("hanzhong_banner_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BannerDO extends BaseDO {

    /**
     * 编号
     */
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 图片地址
     */
    private String picUrl;
    /**
     * 跳转类型（0-无跳转 1-内部页面 2-外部链接）
     */
    private Integer linkType;
    /**
     * 跳转链接
     */
    private String linkUrl;
    /**
     * 跳转业务 id（如课程 id、职位 id 等）
     */
    private Long linkId;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 状态（0-开启 1-关闭），对应 {@link cn.iocoder.yudao.framework.common.enums.CommonStatusEnum}
     */
    private Integer status;
    /**
     * 生效开始时间
     */
    private LocalDateTime startTime;
    /**
     * 生效结束时间
     */
    private LocalDateTime endTime;
    /**
     * 备注
     */
    private String remark;

}
