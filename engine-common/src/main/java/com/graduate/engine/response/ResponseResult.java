package com.graduate.engine.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @anthor jimmy
 * 用于响应请求通用模板
 */
@Getter
@Setter
public class ResponseResult implements Serializable {
    private boolean success = true;
    private Integer code = null;
    private String errMsg = null;
    private Object data = null;

    public ResponseResult(boolean success, Integer code, String errMsg, Object data) {
        this.success = success;
        this.code = code;
        this.errMsg = errMsg;
        this.data = data;
    }

    public ResponseResult(boolean success, Object data) {
        this.success = success;
        this.code = 200;
        this.data = data;
    }

    public ResponseResult(boolean success, String errMsg, Object data) {
        this.success = success;
        this.errMsg = errMsg;
        this.data = data;
    }

    public ResponseResult(boolean success) {
        this.success = success;
    }

    public static ResponseResult build(boolean success, String errMsg, Object data) {
        return new ResponseResult(success, errMsg, data);
    }

    public static ResponseResult buildError(String errMsg) {
        return build(false, errMsg, null);
    }

    public static ResponseResult buildError(int code, String errMsg) {
        return new ResponseResult(false,code, errMsg, null);
    }

    public static ResponseResult buildSuccess(Object data) {
        return build(true, data);
    }

    public static ResponseResult buildSuccess() {
        return build(true, null);
    }

    public static ResponseResult build(boolean success, Object data) {
        return new ResponseResult(success, data);
    }

    public static ResponseResult build(boolean success) {
        return new ResponseResult(success);
    }
}
