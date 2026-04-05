package cn.iocoder.yudao.module.hanzhong.coursecategory.dal.dataobject;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 汉中 课程分类 DO
 *
 * @author hanzhong
 */
@TableName("hanzhong_course_category")
@KeySequence("hanzhong_course_category_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseCategoryDO extends BaseDO {

    /**
     * 编号
     */
    private Long id;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 父分类编号
     */
    private Long parentId;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 状态（0-开启 1-关闭），对应 {@link cn.iocoder.yudao.framework.common.enums.CommonStatusEnum}
     */
    private Integer status;
    /**
     * 图标
     */
    private String icon;
    /**
     * 备注
     */
    private String remark;

}
