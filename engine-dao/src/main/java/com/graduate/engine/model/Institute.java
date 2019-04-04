package com.graduate.engine.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.graduate.engine.vaildator.group.AddGroup;
import com.graduate.engine.vaildator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Institute", description="学会表")
public class Institute implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学会代号")
    @TableId(value = "inst_id", type = IdType.AUTO)
    private Integer instId;

    private String instName;

    private String instShort;

    private String instEngName;

    @ApiModelProperty(value = "学会行业分类")
    private Integer industryId;

    @TableField(exist=false)
    @ApiModelProperty(value = "学会行业分类名称")
    private String indusName;

    @TableField(exist=false)
    @ApiModelProperty(value = "学会行业分类简称")
    private String indusShort;

    @ApiModelProperty(value = "学会地址")
    private String instAddress;

    @ApiModelProperty(value = "学会网址")
    private String instWebsite;

    @ApiModelProperty(value = "学会微信")
    private String instWechat;

    @ApiModelProperty(value = "学会qq")
    private String instQq;

    @ApiModelProperty(value = "学会其他联系方式")
    private String instOthers;

    @ApiModelProperty(value = "学会成立时间")
    private Long instRegisterDate;

    @ApiModelProperty(value = "学会介绍")
    private String instIntroduction;

    @ApiModelProperty(value = "学会备注")
    private String instMemo;

    private Boolean stop;


}
