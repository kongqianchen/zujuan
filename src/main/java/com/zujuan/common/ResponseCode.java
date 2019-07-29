package com.zujuan.common;

/**
 * 服务器状态码
 * 
 * @author Guojian Wang
 * @version
 * @since
 * @Date 2019年7月11日 下午3:40:42
 */
public enum ResponseCode {
	SUCCESS(0, "SUCCESS"), ERROR(1, "ERROR"), NEED_LOGIN(10, "NEED_LOGIN"), ILLEGAL_ARGUMENT(2, "ILLEGAL");

	private final int code;
	private final String desc;

	ResponseCode(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

}
