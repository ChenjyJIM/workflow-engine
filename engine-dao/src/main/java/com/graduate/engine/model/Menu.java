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
@ApiModel(value="Menu", description="菜单")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    private Long parentId;

    @TableField(exist=false)
    @ApiModelProperty(value = "父菜单名称")
    private String parentName;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "菜单名称")
    private String title;

    @ApiModelProperty(value = "菜单URL")
    private String path;

    @ApiModelProperty(value = "菜单URL")
    private Boolean hideInMenu;

    @ApiModelProperty(value = "页面路径")
    private String component;

    @ApiModelProperty(value = "授权(多个用逗号分隔，如：user:list,user:create)")
    private String perms;

    private Long type;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    private Long orderNum;

    private Boolean stop;


}
