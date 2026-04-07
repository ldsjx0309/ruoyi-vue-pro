package cn.iocoder.yudao.module.hanzhong.leveltest.controller.app.vo;

import cn.iocoder.yudao.module.hanzhong.course.controller.app.vo.AppCourseRespVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "用户 APP - 汉中 水平测试结果 Response VO")
@Data
public class AppLevelTestResultRespVO {

    @Schema(description = "测试目标", example = "TOPIK")
    private String target;

    @Schema(description = "得分", example = "80")
    private Integer score;

    @Schema(description = "推荐等级", example = "中级")
    private String recommendedLevel;

    @Schema(description = "推荐课程")
    private List<AppCourseRespVO> recommendedCourses;

    @Schema(description = "提交时间")
    private LocalDateTime submitTime;
}
