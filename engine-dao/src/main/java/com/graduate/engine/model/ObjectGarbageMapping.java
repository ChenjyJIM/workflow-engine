package com.graduate.engine.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "ObjectGarbageMapping", description = "物品垃圾映射")
public class ObjectGarbageMapping {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "物品名称")
    private String name;

    @ApiModelProperty(value = "隶属于哪种垃圾种类")
    private Long garbageType;

}
