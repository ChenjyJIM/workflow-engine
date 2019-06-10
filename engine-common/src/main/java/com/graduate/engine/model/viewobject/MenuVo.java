package com.graduate.engine.model.viewobject;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class MenuVo {
    private Long id;

    private String path;

    private String name;

    private HashMap<String, Object> meta;

    private String component;

    private List<MenuVo> children;
}
