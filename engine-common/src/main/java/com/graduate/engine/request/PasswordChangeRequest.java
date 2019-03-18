package com.graduate.engine.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author jimmy
 */
@Getter
@Setter
public class PasswordChangeRequest {
    private Long loginId;
    private String password;
    private String newPassword;
}
