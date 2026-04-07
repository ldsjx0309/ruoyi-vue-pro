package cn.iocoder.yudao.module.hanzhong.courserating.dal.dataobject;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 汉中 课程评分 DO
 *
 * @author hanzhong
 */
@TableName("hanzhong_course_rating")
@KeySequence("hanzhong_course_rating_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseRatingDO extends BaseDO {

    /** 编号 */
    private Long id;
    /** 用户编号 */
    private Long userId;
    /** 课程编号 */
    private Long courseId;
    /** 课程名称（冗余） */
    private String courseName;
    /** 评分（1-5 星，整数） */
    private Integer rating;
    /** 评价内容（可选） */
    private String comment;

}
