package com.sju.program.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author qhw
 * @date 2020/12/24
 **/
@Data
@AllArgsConstructor
@Builder
public class Result<T> {
	// TODO 增加统一拦截，按Locale设置message字段
	public static final String DEFAULT_SUCCESS_MSG = "操作成功！";

	private boolean success;

	private String message;

	private String code;

	private T data;


	public Result() {
		this.success = true;
		this.code = "10000";
		this.message = DEFAULT_SUCCESS_MSG;
	}

	public Result(T data) {
		this();
		this.data = data;
	}

	public Result(boolean success, String message, String code) {
		this.success = success;
		this.message = message;
		this.code = code;
	}

	public static <T> Result<T> success() {
		return new Result<>();
	}

	public static <T> Result<T> success(T data) {
		return new Result<>(data);
	}

	public static <T> Result<T> fail(String message, String code) {
		return new Result<>(false, message, code);
	}

}
