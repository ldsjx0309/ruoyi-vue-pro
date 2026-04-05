package cn.iocoder.yudao.module.hanzhong.communitypost.dal.dataobject;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 汉中 社区帖子 DO
 *
 * @author hanzhong
 */
@TableName("hanzhong_community_post")
@KeySequence("hanzhong_community_post_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunityPostDO extends BaseDO {

    /** 编号 */
    private Long id;
    /** 发布者用户编号 */
    private Long userId;
    /** 标题 */
    private String title;
    /** 内容 */
    private String content;
    /** 封面图片地址 */
    private String coverUrl;
    /** 分类 */
    private String category;
    /** 浏览量 */
    private Integer viewCount;
    /** 点赞数 */
    private Integer likeCount;
    /** 评论数 */
    private Integer commentCount;
    /** 状态（0-正常 1-已下线） */
    private Integer status;
    /** 备注 */
    private String remark;

}
