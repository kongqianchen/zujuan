package com.zujuan.dao;

import com.zujuan.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkUserPhone(String userPhone);

    User selectLogin(@Param("userphone") String userphone, @Param("password") String password);

    int checkNicknameByUserId(@Param("nickname")String nickname,@Param("userId") Integer userId);

    int checkNickname(String nickname);

    int checkPhoneNum(String phoneNum);

    int register(User user);

    User LoginByPhoneNumAndCredential(@Param(value = "userPhone") String userPhone,@Param(value = "credential") String credential);

    String findCredentialByPhoneNum(String phoneNum);

    void modifyCredential(@Param("phoneNum") String phoneNum, @Param("credential_new") String credential);
}