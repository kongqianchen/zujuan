package com.aliyun.message;

import java.util.Random;

/**
 * @Author Guojian Wang
 * @Date 2019/7/26 10:39
 */
public class GetCodeUtils {
    public static String getCode() {
        StringBuffer buffer = new StringBuffer();
        //随机数字
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            //生成一个6位数字的随机验证码
            buffer.append(random.nextInt(10));
        }
        return buffer.toString();
    }
}
