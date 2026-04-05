package cn.iocoder.yudao.module.hanzhong.coursefavorite.dal.dataobject;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 汉中 课程收藏 DO
 *
 * @author hanzhong
 */
@TableName("hanzhong_course_favorite")
@KeySequence("hanzhong_course_favorite_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseFavoriteDO extends BaseDO {

    /** 编号 */
    private Long id;
    /** 用户编号 */
    private Long userId;
    /** 课程编号 */
    private Long courseId;
    /** 课程名称（冗余） */
    private String courseName;
    /** 课程封面（冗余） */
    private String coverUrl;

}
