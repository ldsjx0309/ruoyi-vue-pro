package cn.iocoder.yudao.module.hanzhong.course.dal.dataobject;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 汉中 课程 DO
 *
 * @author hanzhong
 */
@TableName("hanzhong_course")
@KeySequence("hanzhong_course_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDO extends BaseDO {

    /**
     * 编号
     */
    private Long id;
    /**
     * 分类编号
     */
    private Long categoryId;
    /**
     * 课程标题
     */
    private String title;
    /**
     * 封面图片地址
     */
    private String coverUrl;
    /**
     * 简介
     */
    private String summary;
    /**
     * 课程内容
     */
    private String content;
    /**
     * 讲师名称
     */
    private String teacherName;
    /**
     * 价格（分）
     */
    private Integer price;
    /**
     * 原价（分）
     */
    private Integer originalPrice;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 状态（0-开启 1-关闭），对应 {@link cn.iocoder.yudao.framework.common.enums.CommonStatusEnum}
     */
    private Integer status;
    /**
     * 浏览量
     */
    private Integer viewCount;
    /**
     * 报名人数
     */
    private Integer enrollCount;
    /**
     * 备注
     */
    private String remark;

}
