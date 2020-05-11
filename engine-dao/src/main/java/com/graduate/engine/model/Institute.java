package com.graduate.engine.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Institute", description = "学会表")
public class Institute implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "inst_id", type = IdType.AUTO)
    private Long instId;

    private String instName;

    private String instShort;

    private String instEngName;

    @ApiModelProperty(value = "行业分类")
    private Integer industryId;

    @TableField(exist = false)
    @ApiModelProperty(value = "行业分类名称")
    private String indusName;

    @TableField(exist = false)
    @ApiModelProperty(value = "行业分类简称")
    private String indusShort;

    @ApiModelProperty(value = "地址")
    private String instAddress;

    @ApiModelProperty(value = "网址")
    private String instWebsite;

    @ApiModelProperty(value = "微信")
    private String instWechat;

    @ApiModelProperty(value = "qq")
    private String instQq;

    @ApiModelProperty(value = "其他联系方式")
    private String instOthers;

    private Long instRegisterDate;

    /**
     * 将时间转换为String类型，方便前端显示
     */
    @TableField(exist = false)
    private String registerTime;

    @ApiModelProperty(value = "介绍")
    private String instIntroduction;

    @ApiModelProperty(value = "备注")
    private String instMemo;

    private Boolean stop;


}
