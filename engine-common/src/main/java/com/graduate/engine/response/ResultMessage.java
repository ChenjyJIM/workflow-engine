package com.graduate.engine.response;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class ResultMessage implements Serializable {

	private boolean success = true;
	private Integer code = null;
	private String errMsg = null;
	private Object data = null;

	public ResultMessage() {
	}

	public ResultMessage(Object data) {
		this.data = data;
	}

	public ResultMessage(boolean success, String errMsg) {
		this.success = success;
		this.errMsg = errMsg;
	}

	public ResultMessage(boolean success, Integer code, String errMsg) {
		this.success = success;
		this.code = code;
		this.errMsg = errMsg;
	}

	public ResultMessage(boolean success, String errMsg, Object data) {
		this.success = success;
		this.errMsg = errMsg;
		this.data = data;
	}

	public ResultMessage(boolean success, Integer code, String errMsg, Object data) {
		this.success = success;
		this.code = code;
		this.errMsg = errMsg;
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public ResultMessage setSuccess(boolean success) {
		this.success = success;
		return this;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public ResultMessage setErrMsg(String errMsg) {
		this.errMsg = errMsg;
		return this;
	}

	public Object getData() {
		return data;
	}

	public ResultMessage setData(Object data) {
		this.data = data;
		return this;
	}

	public static ResultMessage buildSuccess(Object obj){
		ResultMessage message = new ResultMessage();
		message.setSuccess(true);
		message.setCode(0);
		message.setData(obj);
		return message;
	}

	public static ResultMessage buildFail(String errorMsg){
		ResultMessage message = new ResultMessage();
		message.setSuccess(false);
		message.setCode(-1);
		message.setErrMsg(errorMsg);
		return message;
	}
}
