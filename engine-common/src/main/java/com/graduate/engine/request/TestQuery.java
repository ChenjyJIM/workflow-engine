package com.graduate.engine.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TestQuery extends PageQuery implements Serializable {
    private Long id;
}
