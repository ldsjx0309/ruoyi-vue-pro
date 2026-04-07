package cn.iocoder.yudao.module.hanzhong.courseorder.dal.dataobject;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 汉中 课程订单 DO
 *
 * @author hanzhong
 */
@TableName("hanzhong_course_order")
@KeySequence("hanzhong_course_order_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseOrderDO extends BaseDO {

    /** 编号 */
    private Long id;
    /** 订单编号 */
    private String orderNo;
    /** 用户编号 */
    private Long userId;
    /** 课程编号 */
    private Long courseId;
    /** 课程名称（快照） */
    private String courseName;
    /** 课程封面（快照） */
    private String coverUrl;
    /** 实付价格（分） */
    private Integer price;
    /** 支付方式 */
    private String paymentMethod;
    /** 状态（0-待支付 1-已支付 2-已取消 3-已退款） */
    private Integer status;
    /** 支付时间 */
    private LocalDateTime payTime;
    /** 取消时间 */
    private LocalDateTime cancelTime;
    /** 备注 */
    private String remark;

}
