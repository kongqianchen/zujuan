package com.zujuan.service.impl;

import com.zujuan.common.Const;
import com.zujuan.common.ServerResponse;
import com.zujuan.dao.UserMapper;
import com.zujuan.pojo.User;
import com.zujuan.service.IUserService;
import com.zujuan.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Guojian Wang
 * @Date 2019/7/23 14:01
 */
@Service
public class IUSerServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 注册昵称检查
     *
     * @param nickname 昵称
     * @Author: shoucan Han
     * @Description:检验用户输入的昵称是否可用
     * @Date: 14:01 2019/7/25
     * @Param:
     * @Return: java.lang.Boolean
     */
    @Override
    public Boolean checkNickname(String nickname) {
        int count = userMapper.checkNickname(nickname);
        return count == 0 ? true : false;
    }

    /**
     * 注册号码检查
     *
     * @param phoneNum 电话号码
     * @Author: shoucan Han
     * @Description:检验用户输入的电话号码是否可用
     * @Date: 14:02 2019/7/25
     * @Param:
     * @Return: java.lang.Boolean
     */
    @Override
    public Boolean checkPhoneNum(String phoneNum) {
        int count = userMapper.checkPhoneNum(phoneNum);
        return count == 0 ? true : false;
    }

    /**
     * 注册
     *
     * @param nickname   昵称
     * @param credential 密码
     * @param user_phone 电话号码
     * @param gender     性别
     * @Author: shoucan Han
     * @Description:用户注册的方法
     * @Date: 14:01 2019/7/25
     * @Param:
     * @Return: com.zujuan.common.ServerResponse<java.lang.Object>
     */
    @Override
    public ServerResponse<Object> register(String nickname, String credential, String user_phone, String gender) {

        //判断用户名和电话号码是否可用
        Boolean b1 = this.checkNickname(nickname);
        if (!b1) {
            return ServerResponse.createByErrorMessage("用户名已存在");
        }
        Boolean b2 = this.checkPhoneNum(user_phone);
        if (!b2) {
            return ServerResponse.createByErrorMessage("电话号码已存在");
        }
        //对密码进行加密(已经改为前端加密，后端不用再次加密)
       // credential = MD5Util.MD5EncodeUtf8(credential);
        //性别
        int gender_int = Integer.parseInt(gender);
        User user = new User();
        user.setNickname(nickname);
        user.setCredential(credential);
        user.setUserPhone(user_phone);
        user.setGender(gender_int);
        int id = userMapper.register(user);
        if (id < 0) {
            //说明注册失败
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }


    /**
     * 通过手机号登录
     *
     * @param userPhone  电话号码
     * @param credential 密码
     * @Author: shoucan Han
     * @Description: 用户登录  电话号码+密码
     * @Date: 14:09 2019/7/25
     * @Param:
     * @Return: com.zujuan.common.ServerResponse<com.zujuan.pojo.Users>
     */

    @Override
    public ServerResponse<User> loginByPhoneNumAndCredential(String userPhone, String credential) {
        int resultCount = userMapper.checkPhoneNum(userPhone);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("登陆失败，请检查您的用户名或密码");
        }

        //todo  密码登录MD5
        credential = MD5Util.MD5EncodeUtf8(credential);

        User user = userMapper.LoginByPhoneNumAndCredential(userPhone, credential);
        if (user == null) {
            return ServerResponse.createByErrorMessage("登陆失败，请检查您的用户名或密码");
        }

        user.setUserPhone(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功", user);
    }

    /**
     *  密码修改
     * @param phoneNum  电话号码
     * @param credential_old  旧密码
     * @param credential_new  新密码
     * @return
     */
    @Override
    public ServerResponse modifyCredential(String phoneNum, String credential_old, String credential_new) {
        //先判断用户输入的旧密码是否正确
       String credential_mysql = userMapper.findCredentialByPhoneNum(phoneNum);
       Boolean b = credential_mysql.equals(credential_old);
       if(!b){
           //用户输入的旧密码不正确
           return ServerResponse.createByErrorMessage("您输入的密码有误");
       }
       //更新密码

        userMapper.modifyCredential(phoneNum,credential_new);
       return ServerResponse.createBySuccessMessage("修改密码成功");
    }


    /**
     * 校验是否是管理员
     * backend
     *
     * @param user
     * @return
     */
    @Override
    public ServerResponse checkUserRole(User user) {
        if (user != null && user.getPermission().intValue() == Const.Role.ROLE_ADMIN) {
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    @Override
    public ServerResponse<User> getInformation(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            return ServerResponse.createByErrorMessage("找不到当前用户");
        }
        user.setCredential(StringUtils.EMPTY);
        return ServerResponse.createBySuccess(user);
    }

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    @Override
    public ServerResponse<User> updateInformation(User user) {
        // userPhone是不能被更新的
        // NickName也要进行一个校验，校验新的NickName是不是已经存在，并且存在的NickName如果相同的话，不能使我们当前的这个用户的。

        int resultCount = userMapper.checkNicknameByUserId(user.getNickname(), user.getUserId());
        if (resultCount > 0) {
            return ServerResponse.createByErrorMessage("昵称已经存在，请更换昵称再尝试更新");
        }
        User updateUser = new User();
        updateUser.setUserId(user.getUserId());
        updateUser.setBirthday(user.getBirthday());
        updateUser.setGender(user.getGender());
        updateUser.setNickname(user.getNickname());

        int updateCount = userMapper.updateByPrimaryKeySelective(updateUser);
        if (updateCount > 0) {
            return ServerResponse.createBySuccess("更新个人信息成功", updateUser);
        }
        return ServerResponse.createByErrorMessage("更新个人信息失败");
    }
}
