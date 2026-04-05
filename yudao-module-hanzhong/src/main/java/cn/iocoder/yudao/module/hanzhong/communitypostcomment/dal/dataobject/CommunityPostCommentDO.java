package cn.iocoder.yudao.module.hanzhong.communitypostcomment.dal.dataobject;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 汉中 社区帖子评论 DO
 *
 * @author hanzhong
 */
@TableName("hanzhong_community_post_comment")
@KeySequence("hanzhong_community_post_comment_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunityPostCommentDO extends BaseDO {

    /** 编号 */
    private Long id;
    /** 帖子编号 */
    private Long postId;
    /** 评论用户编号 */
    private Long userId;
    /** 评论内容 */
    private String content;
    /** 父评论编号（0-顶层评论） */
    private Long parentId;
    /** 状态（0-正常 1-已屏蔽） */
    private Integer status;

}
