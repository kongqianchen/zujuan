package com.zujuan.controller.protal;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.message.GetCodeUtils;
import com.aliyuncs.exceptions.ClientException;
import com.zujuan.common.Const;
import com.zujuan.common.ServerResponse;
import com.zujuan.pojo.User;
import com.zujuan.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author Guojian Wang
 * @Date 2019/7/24 15:03
 */
@RestController
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private IUserService iUserService;

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session) {
        // service-->mybatis-->dao
        ServerResponse<User> response = iUserService.loginByPhoneNumAndCredential(username, password);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    /**
     * 用户登出
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    /**
     * @param phoneNum 电话号码
     * @param request
     * @Author: shoucan Han
     * @Description:用户点击获取验证码，请求此接口，发送短信验证码到手机号
     * @Date: 14:06 2019/7/25
     * @Param:
     * @Return: com.zujuan.common.ServerResponse
     */
    @RequestMapping("getPhoneCode.do")
    public ServerResponse getPhoneCode(String phoneNum, HttpServletRequest request) throws ClientException {
//        return HandlerMessage.sendSMS(phoneNum, request);
        String code = GetCodeUtils.getCode();
        // todo sms
        //CommonResponse response = .sendSms(phoneNum,code);
//        if(response.getHttpStatus() != null && sendSmsResponse.getCode().equals("OK")){
            //验证码发送成功
            //将验证码存入session中，同时存入创建时间
            HttpSession session = request.getSession();
            net.sf.json.JSONObject json = new net.sf.json.JSONObject();
            json.put("verifyCode", code);
            json.put("createTime", System.currentTimeMillis());
            session.setAttribute("verifyCode", json);
            //返回操作成功
            return ServerResponse.createBySuccessMessage("验证码发送成功");
        //}
            //return ServerResponse.createBySuccessMessage("验证码发送失败");
    }

    /**
     * @param nickname   昵称
     * @param credential 密码
     * @param user_phone 电话号码
     * @param verifyCode 验证码
     * @param gender     性别
     * @param request
     * @Author: shoucan Han
     * @Description:用户注册
     * @Date: 14:08 2019/7/25
     * @Param:
     * @Return: com.zujuan.common.ServerResponse
     */
    @RequestMapping("register.do")
    public ServerResponse register(String nickname,
                                   String credential,
                                   String user_phone,
                                   String verifyCode,
                                   String gender,
                                   HttpServletRequest request) {
        JSONObject json = (JSONObject) request.getSession().getAttribute("verifyCode");
        if (json == null) {
            return ServerResponse.createByErrorMessage("请先验证");
        }
        if (!json.getString("verifyCode").equals(verifyCode)) {
            return ServerResponse.createByErrorMessage("验证码错误");
        }
        //验证码有效期为5分钟
        if ((System.currentTimeMillis() - json.getLong("createTime")) > 1000 * 60 * 5) {
            return ServerResponse.createByErrorMessage("验证码过期");
        }
        //将信息存入数据库
        return iUserService.register(nickname, credential, user_phone, gender);
    }

    /**
     * @param nickname 昵称
     * @Author: shoucan Han
     * @Description:检验用户输入的昵称是否可用
     * @Date: 14:05 2019/7/25
     * @Param:
     * @Return: java.lang.Boolean
     */
    @RequestMapping("checkNickname.do")
    public Boolean checkUsername(@RequestParam(value = "nickname", required = true) String nickname) {
        return iUserService.checkNickname(nickname);
    }

    /**
     * @param phoneNum 电话号码
     * @Author: shoucan Han
     * @Description:检验用户输入的电话号码是否可用
     * @Date: 14:05 2019/7/25
     * @Param:
     * @Return: java.lang.Boolean
     */
    @RequestMapping("checkPhoneNum.do")
    public Boolean checkPhoneNum(@RequestParam(value = "phoneNum", required = true) String phoneNum) {
        return iUserService.checkPhoneNum(phoneNum);
    }

    /**
     * 获取用户信息
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "get_user_info.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user != null) {
            return ServerResponse.createBySuccess(user);
        }

        return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
    }


    /**
     * 更新信息
     *
     * @param session
     * @param user
     * @return
     */
    @RequestMapping(value = "update_information.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> update_information(HttpSession session, User user) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        user.setUserId(currentUser.getUserId());

        ServerResponse<User> response = iUserService.updateInformation(user);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    /**
     * 密码修改接口
     *
     * @param phoneNum       电话号码
     * @param credential_old 旧密码
     * @param credential_new 新密码
     * @return
     */
    @RequestMapping("modifyCredential.do")
    public ServerResponse modifyCredential(String phoneNum, String credential_old, String credential_new) {
        return iUserService.modifyCredential(phoneNum, credential_old, credential_new);
    }

}
