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
 * 个人会员申请详细信息表
 * </p>
 *
 * @author silicon
 * @since 2019-05-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PersonMemberApplicationDetails", description="个人会员申请详细信息表")
public class PersonMemberApplicationDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "details_id", type = IdType.AUTO)
    private Long detailsId;

    @ApiModelProperty(value = "申请学会id")
    private Long instId;

    private String name;

    private String sex;

    private Long birthday;

    /**
     * 将时间转换为String类型，方便前端显示
     */
    @TableField(exist=false)
    private String formatBirthday;

    @ApiModelProperty(value = "民族")
    private String ethnic;

    @ApiModelProperty(value = "党派")
    private String partisan;

    private String mail;

    private String phone1;

    private String phone2;

    private String qq;

    private String wechat;

    @ApiModelProperty(value = "其他联系方式")
    private String others;

    @ApiModelProperty(value = "社会任职情况")
    private String personPublicDuty;

    @ApiModelProperty(value = "获奖情况")
    private String personAwards;

    @ApiModelProperty(value = "最高学历")
    private Long educationId;

    @ApiModelProperty(value = "申请表id")
    private Long applicationId;

    private String memo;


}
