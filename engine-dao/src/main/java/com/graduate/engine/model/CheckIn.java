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
 * 报名签到
 * </p>
 *
 * @author silicon
 * @since 2019-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "CheckIn", description = "报名签到")
public class CheckIn implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "check_id", type = IdType.AUTO)
    private Long checkId;

    @ApiModelProperty(value = "报名项目ID")
    private Long signId;

    @ApiModelProperty(value = "报名人ID")
    private Long loginIn;

    @ApiModelProperty(value = "是否签到")
    private Boolean signUp;


}
