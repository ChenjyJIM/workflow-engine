package com.graduate.engine.model;

import lombok.Data;

@Data
public class Role {
    private Integer roleId;

    private String roleName;

    private String roleClass;

    private String memo;

    private Boolean stop;
}
