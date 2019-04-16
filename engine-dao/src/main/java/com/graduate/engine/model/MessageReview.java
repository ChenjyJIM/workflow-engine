package com.graduate.engine.model;

import com.baomidou.mybatisplus.annotation.IdType;
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
@ApiModel(value="MessageReview", description="信息审核表")
public class MessageReview implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "接受消息角色ID")
    private Long roleId;

    @ApiModelProperty(value = "信息ID")
    private Long messageId;

    @ApiModelProperty(value = "状态，0：待审核；1：审核通过；2：审核不通过")
    private Long status;

    @ApiModelProperty(value = "审核人ID")
    private Long reviewPerson;


}
