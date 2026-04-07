package cn.iocoder.yudao.module.hanzhong.leveltest.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Schema(description = "用户 APP - 汉中 水平测试题目 Response VO")
@Data
public class AppLevelTestQuestionRespVO {

    @Schema(description = "题目编号", example = "1")
    private Integer id;

    @Schema(description = "测试目标", example = "TOPIK")
    private String target;

    @Schema(description = "题目内容")
    private String question;

    @Schema(description = "音频地址")
    private String audioUrl;

    @Schema(description = "选项")
    private List<String> options;

    @JsonIgnore
    @Schema(description = "正确答案索引", hidden = true)
    private Integer answerIndex;
}
