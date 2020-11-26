package com.sju.program.message;

import io.swagger.annotations.ApiModelProperty;

public class Test {

	@ApiModelProperty(name = "code",value = "状态码")
	private String code;
	@ApiModelProperty(name = "msg",value = "提示信息")
	private String msg;
	@ApiModelProperty(name = "data",value = "数据")
	private String data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
