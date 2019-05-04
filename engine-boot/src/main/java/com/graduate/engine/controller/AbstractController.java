package com.graduate.engine.controller;

import com.graduate.engine.model.Login;
import org.apache.shiro.SecurityUtils;

public abstract class AbstractController {
    protected Login getUser() {
        return (Login) SecurityUtils.getSubject().getPrincipal();
    }

    protected Long getUserId() {
        return getUser().getLoginId();
    }

    protected Long getPersonId() {
        return getUser().getPersonId();
    }
}
