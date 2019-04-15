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
@ApiModel(value="UnionInstMapper", description="学联体和学会关系映射表")
public class UnionInstMapper implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "union_inst_id", type = IdType.AUTO)
    private Long unionInstId;

    private Long unionId;

    private Long instId;

    private Boolean stop;
}
