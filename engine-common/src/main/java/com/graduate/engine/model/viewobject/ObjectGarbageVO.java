package com.graduate.engine.model.viewobject;

import lombok.Data;

import java.io.Serializable;

@Data
public class ObjectGarbageVO implements Serializable {

    private Long id;

    private String name;

    private Long garbageType;

    private String garbageDetail;

    private String garbageName;

    private String img;

}
