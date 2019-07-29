package com.zujuan.common;

/**
 * 常量
 * 
 * @author Guojian Wang
 * @version
 * @since
 * @Date 2019年7月11日 下午3:57:49
 */
public class Const {

	public static final String CURRENT_USER = "currentUser";

	public static final String USER_PHONE = "phone";

	public interface Role {
		// 学生用户
		int ROLE_STUDENT = 0;
		// 教师用户
		int ROLE_TEACHER = 1;
		// 管理员
		int ROLE_ADMIN = 2;
	}

}
