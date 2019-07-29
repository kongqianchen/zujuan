package com.zujuan.service;

import com.zujuan.common.ServerResponse;
import com.zujuan.pojo.User;

public interface IUserService {
    ServerResponse<Object> register(String nickname, String credential, String user_phone, String gender);

    ServerResponse<User> getInformation(Integer userId);

    ServerResponse checkUserRole(User user);

    ServerResponse<User> updateInformation(User user);

    Boolean checkNickname(String nickname);

    Boolean checkPhoneNum(String phoneNum);

    ServerResponse<User> loginByPhoneNumAndCredential(String userPhone, String credential);

    ServerResponse modifyCredential(String phoneNum, String credential_old, String credential_new);
}
