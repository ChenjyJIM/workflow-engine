package com.graduate.engine.model;

import lombok.Data;

@Data
public class PersonRoleMapper {
    private Long personRoleId;

    private Long personId;

    private Integer roleId;

    private String memo;

    private Boolean stop;
}
