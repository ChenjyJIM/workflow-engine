package com.graduate.engine.model;

import lombok.Data;

/**
 * @author jimmy
 */
@Data
public class Login {
    private Long loginId;
    private String loginName;
    private String loginPassword;
    private Long personId;
    private Long guestId;
    private Boolean stop;
}
