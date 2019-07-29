package com.aliyun.message;

import com.zujuan.common.ServerResponse;
import com.zujuan.util.PropertiesUtil;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 短信验证
 *
 * @Author Guojian Wang
 * @Date 2019/7/24 10:25
 */
public class HandlerMessage {
    /**
     * 发送短信
     *
     * @param phoneNum
     * @param request
     * @return
     */
    public static ServerResponse sendSMS(String phoneNum, HttpServletRequest request) {
        //调用生成验证码的工具类，生成6位数字随机验证码
        String code = GetCodeUtils.getCode();
        String result = null;
        //请求接口地址
        String url = PropertiesUtil.getProperty("url");
        //请求参数
        Map params = new HashMap<>();
        //接受短信的用户手机号码
        params.put("mobile", phoneNum);
        //您申请的短信模板ID，根据实际情况修改
        params.put("tpl_id", "174922");
        //您设置的模板变量，根据实际情况修改
        params.put("tpl_value", "#code#=" + code);
        //应用APPKEY(应用详细页查询)
        params.put("key", "9bff5b7a66b2f9c5c273fa4176783e32");
        try {
            result = net(url, params, "GET");
            net.sf.json.JSONObject object = net.sf.json.JSONObject.fromObject(result);
            if (object.getInt("error_code") != 0) {
                //发送短信失败
                return ServerResponse.createByErrorMessage("验证码发送失败，请检验您的电话号码或稍后重试");
            }
            //将验证码存入session中，同时存入创建时间
            HttpSession session = request.getSession();
            JSONObject json = new JSONObject();
            json.put("verifyCode", code);
            json.put("createTime", System.currentTimeMillis());
            session.setAttribute("verifyCode", json);
            //返回操作成功
            return ServerResponse.createBySuccessMessage("验证码发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createBySuccessMessage("服务器正在维护，请稍后重试");
        }
    }

    /**
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return 网络请求字符串
     * @throws Exception
     */
    private static String net(String strUrl, Map params, String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if (method == null || method.equals("GET")) {
                strUrl = strUrl + "?" + urlEncode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if (method == null || method.equals("GET")) {
                conn.setRequestMethod("GET");
            } else {
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", PropertiesUtil.getProperty("userAgent"));
            conn.setUseCaches(false);
            conn.setConnectTimeout(Integer.parseInt(PropertiesUtil.getProperty("DEF_CONN_TIMEOUT")));
            conn.setReadTimeout(Integer.parseInt(PropertiesUtil.getProperty("DEF_READ_TIMEOUT")));
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params != null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlEncode(params));
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, PropertiesUtil.getProperty("DEF_CHATSET")));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }

    /**
     * 将map型转为请求参数型
     *
     * @param data
     * @return
     */
    private static String urlEncode(Map<String, String> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
