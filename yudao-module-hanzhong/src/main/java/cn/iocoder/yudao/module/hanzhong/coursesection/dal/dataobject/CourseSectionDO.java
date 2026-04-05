package cn.iocoder.yudao.module.hanzhong.coursesection.dal.dataobject;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 汉中 课程章节 DO
 *
 * @author hanzhong
 */
@TableName("hanzhong_course_section")
@KeySequence("hanzhong_course_section_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseSectionDO extends BaseDO {

    /** 编号 */
    private Long id;
    /** 所属课程编号 */
    private Long courseId;
    /** 章节标题 */
    private String title;
    /** 章节描述 */
    private String description;
    /** 视频地址 */
    private String videoUrl;
    /** 视频时长（秒） */
    private Integer duration;
    /** 排序 */
    private Integer sort;
    /** 是否免费试看（0-否 1-是） */
    private Integer freePreview;
    /** 状态（0-开启 1-关闭） */
    private Integer status;
    /** 备注 */
    private String remark;

}
