package com.graduate.engine.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 个人会员申请记录表
 * </p>
 *
 * @author lianglili
 * @since 2019-05-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "PersonMemberApplicationRecord", description = "个人会员申请记录表")
public class PersonMemberApplicationRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "record_id", type = IdType.AUTO)
    private Long recordId;

    @ApiModelProperty(value = "申请表id")
    private Long applicationId;

    @ApiModelProperty(value = "修改时间")
    private Long editTime;

    @ApiModelProperty(value = "修改人")
    private Long editPerson;

    @ApiModelProperty(value = "记录信息")
    private String message;


}
