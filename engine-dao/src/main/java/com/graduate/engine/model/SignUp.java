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
import java.math.BigDecimal;

/**
 * <p>
 * 报名管理
 * </p>
 *
 * @author silicon
 * @since 2019-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "SignUp", description = "报名管理")
public class SignUp implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "sign_id", type = IdType.AUTO)
    private Long signId;

    @ApiModelProperty(value = "报名项目名称")
    private String signName;

    @ApiModelProperty(value = "报名项目简称")
    private String signShort;

    @ApiModelProperty(value = "报名项目英文名称")
    private String signEngName;

    @ApiModelProperty(value = "本报名项目行业分类")
    private Long industryId;

    @TableField(exist = false)
    @ApiModelProperty(value = "本报名项目行业分类名称")
    private String industryName;

    @ApiModelProperty(value = "报名开始时间")
    private Long signDateFrom;

    @ApiModelProperty(value = "报名截止时间")
    private Long signDateTo;

    /**
     * 将时间转换为String类型，方便前端显示
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "报名开始时间")
    private String formatSignDateFrom;

    @TableField(exist = false)
    @ApiModelProperty(value = "报名截止时间")
    private String formatSignDateTo;

    @ApiModelProperty(value = "报名通知")
    private String signForm;

    @ApiModelProperty(value = "活动报名总人数")
    private Long actTotalNum;

    @TableField(exist = false)
    @ApiModelProperty(value = "用户项目关系  0：未报名；1：报名未签到；2：报名已签到")
    private Long status;

    @ApiModelProperty(value = "是否APP签到")
    private Boolean appCheck;

    @ApiModelProperty(value = "纬度")
    private Double lat;

    @ApiModelProperty(value = "经度")
    private Double lng;

    @ApiModelProperty(value = "半径，单位m")
    private Double radius;

    @ApiModelProperty(value = "报名人数")
    private Long signUpNum;

    @ApiModelProperty(value = "签到人数")
    private Long checkInNum;

    private Boolean stop;

    private Boolean startSignUp;


}
