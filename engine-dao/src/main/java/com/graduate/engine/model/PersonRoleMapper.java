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
@ApiModel(value="PersonRoleMapper", description="人员角色映射表")
public class PersonRoleMapper implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "person_role_id", type = IdType.AUTO)
    private Long personRoleId;

    private Long personId;

    private Long roleId;

    private String memo;

    private Boolean stop;


}
