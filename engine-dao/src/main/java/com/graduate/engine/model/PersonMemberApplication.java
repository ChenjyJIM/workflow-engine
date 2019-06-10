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

/**
 * <p>
 * 个人会员申请表
 * </p>
 *
 * @author silicon
 * @since 2019-05-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "PersonMemberApplication", description = "个人会员申请表")
public class PersonMemberApplication implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "application_id", type = IdType.AUTO)
    private Long applicationId;

    @ApiModelProperty(value = "申请用户id")
    private Long loginId;

    @TableField(exist = false)
    @ApiModelProperty(value = "申请用户姓名")
    private String personName;

    @ApiModelProperty(value = "申请时间")
    private Long addTime;

    @ApiModelProperty(value = "状态，0：待审核；1：审核通过；2：审核不通过")
    private Long status;

    @ApiModelProperty(value = "最后修改时间")
    private Long lastEditTime;


}
