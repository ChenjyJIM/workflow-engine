package com.graduate.engine.response;

public class ResultMessageBuilder {

	public static ResultMessage build() {
		return new ResultMessage();
	}

	public static ResultMessage build(Object data) {
		return new ResultMessage(data);
	}

	public static ResultMessage build(boolean success, String errMsg) {
		return new ResultMessage(success, errMsg);
	}

	public static ResultMessage build(boolean success, Integer code, String errMsg) {
		return new ResultMessage(success, code, errMsg);
	}

	public static ResultMessage build(boolean success, String errMsg, Object data) {
		return new ResultMessage(success, errMsg, data);
	}

	public static ResultMessage build(boolean success, Integer code, String errMsg, Object data) {
		return new ResultMessage(success, code, errMsg, data);
	}

	public static ResultMessage buildSuccess(Object data) {
		return new ResultMessage(data);
	}

	public static ResultMessage buildFail(String errorMessage) {
		return new ResultMessage(false, errorMessage);
	}

	public static ResultMessageRaw buildRaw(String jsonStr) {
		return new ResultMessageRaw(jsonStr);
	}

	public static class ResultMessageRaw {

		private String jsonStr = null;

		public ResultMessageRaw() {

		}

		public ResultMessageRaw(String jsonStr) {
			this.jsonStr = jsonStr;
		}

		public String getJsonStr() {
			return jsonStr;
		}

		public void setJsonStr(String jsonStr) {
			this.jsonStr = jsonStr;
		}

		public String toJSONString() {
			StringBuilder buff = new StringBuilder("{\"success\": true, \"data\": ");
			buff.append(jsonStr).append("}");
			return buff.toString();
		}

	}

}
