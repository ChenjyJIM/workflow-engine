package com.graduate.engine.model;

import com.baomidou.mybatisplus.annotation.IdType;
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
@ApiModel(value = "Industry", description = "行业分类")
public class Industry implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "industry_id", type = IdType.AUTO)
    private Long industryId;

    @ApiModelProperty(value = "行业名称")
    @NotBlank(message = "行业名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String indusName;

    @ApiModelProperty(value = "简称")
    @NotBlank(message = "行业简称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String indusShort;

    private Boolean stop;


}
