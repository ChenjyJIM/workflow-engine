package com.graduate.engine.model.viewobject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVo {
    private Long loginId;
    private String loginName;
    private Long type;
    private Long stop;
}
