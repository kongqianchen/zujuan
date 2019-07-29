package com.zujuan.common;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * 服务器状态
 * 
 * @author Guojian Wang
 * @version
 * @since
 * @Date 2019年7月11日 下午3:30:10
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
// 保证序列化json的时候，如果是null对象，key也会消失
public class ServerResponse<T> {
	private int status;
	private String msg;
	private T data;

	public int getStatus() {
		return status;
	}

	public String getMsg() {
		return msg;
	}

	public T getData() {
		return data;
	}

	/**
	 * 返回状态
	 * 
	 * @param status
	 */
	private ServerResponse(int status) {
		this.status = status;
	}

	/**
	 * 返回状态 对象
	 * 
	 * @param status
	 * @param data
	 */
	private ServerResponse(int status, T data) {
		this.status = status;
		this.data = data;
	}

	/**
	 * 返回状态 消息 对象
	 * 
	 * @param status
	 * @param msg
	 * @param data
	 */
	private ServerResponse(int status, String msg, T data) {
		this.status = status;
		this.data = data;
		this.msg = msg;
	}

	/**
	 * 返回状态 消息
	 * 
	 * @param status
	 * @param msg
	 */
	private ServerResponse(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	@JsonIgnore
	public boolean isSuccess() {
		return this.status == ResponseCode.SUCCESS.getCode();
	}

	public static <T> ServerResponse<T> createBySuccess() {
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
	}

	public static <T> ServerResponse<T> createBySuccessMessage(String msg) {
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg);
	}

	public static <T> ServerResponse<T> createBySuccess(T data) {
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), data);
	}

	public static <T> ServerResponse<T> createBySuccess(String msg, T data) {
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg, data);
	}

	public static <T> ServerResponse<T> createByError() {
		return new ServerResponse<T>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
	}

	public static <T> ServerResponse<T> createByErrorMessage(String errorMessage) {
		return new ServerResponse<T>(ResponseCode.ERROR.getCode(), errorMessage);
	}

	public static <T> ServerResponse<T> createByErrorMessage(int errorCode, String errorMessage) {
		return new ServerResponse<T>(errorCode, errorMessage);
	}
}
