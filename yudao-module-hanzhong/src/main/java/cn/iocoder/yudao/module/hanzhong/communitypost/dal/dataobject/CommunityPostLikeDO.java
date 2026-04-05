package cn.iocoder.yudao.module.hanzhong.communitypost.dal.dataobject;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 汉中 社区帖子点赞 DO
 *
 * @author hanzhong
 */
@TableName("hanzhong_community_post_like")
@KeySequence("hanzhong_community_post_like_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunityPostLikeDO extends BaseDO {

    /** 编号 */
    private Long id;
    /** 点赞用户编号 */
    private Long userId;
    /** 帖子编号 */
    private Long postId;

}
