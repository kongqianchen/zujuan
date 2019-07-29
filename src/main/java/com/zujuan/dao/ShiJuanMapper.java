package com.zujuan.dao;

import com.zujuan.pojo.ShiJuan;

public interface ShiJuanMapper {
    int deleteByPrimaryKey(Integer shijuanId);

    int insert(ShiJuan record);

    int insertSelective(ShiJuan record);

    ShiJuan selectByPrimaryKey(Integer shijuanId);

    int updateByPrimaryKeySelective(ShiJuan record);

    int updateByPrimaryKey(ShiJuan record);
}