package com.graduate.engine.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@ApiModel(value="Union", description="学联体表")
@TableName(value = "`union`")
public class Union implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学联体代号")
    @TableId(value = "union_id", type = IdType.AUTO)
    private Long unionId;

    @ApiModelProperty(value = "学联体名称")
    @NotBlank(message="学联体名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String unionName;

    @ApiModelProperty(value = "学联体简称")
    @NotBlank(message="学联体简称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String unionShort;

    @ApiModelProperty(value = "学联体英文名")
    private String unionEngName;

    @ApiModelProperty(value = "学联体地址")
    private String unionAddress;

    private String unionWebsite;

    private String unionWechat;

    private String unionQq;

    private String unionOthers;

    @ApiModelProperty(value = "学联体成立时间")
    private Long unionRegisterDate;

    @ApiModelProperty(value = "学联体简介")
    private String unionIntroduction;

    @ApiModelProperty(value = "学联体备注")
    private String unionMemo;

    private Boolean stop;

}
