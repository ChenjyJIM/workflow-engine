package com.graduate.engine.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
public class UnionRequest implements Serializable {
    private Long unionId;
    private List<Long> initId;
}
