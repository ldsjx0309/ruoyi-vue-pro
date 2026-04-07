package cn.iocoder.yudao.module.hanzhong.card.controller.app.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "用户 APP - 汉中 名片目录分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AppCardPageReqVO extends PageParam {

    @Schema(description = "关键字（姓名/公司/标签）", example = "教育")
    private String keyword;

    @Schema(description = "分组名称", example = "重点人脉")
    private String groupName;

    @Schema(description = "国别", example = "韩国")
    private String country;
}
