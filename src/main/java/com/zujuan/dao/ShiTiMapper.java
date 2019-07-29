package com.zujuan.dao;

import com.zujuan.pojo.ShiTi;

public interface ShiTiMapper {
    int deleteByPrimaryKey(Integer shitiId);

    int insert(ShiTi record);

    int insertSelective(ShiTi record);

    ShiTi selectByPrimaryKey(Integer shitiId);

    int updateByPrimaryKeySelective(ShiTi record);

    int updateByPrimaryKey(ShiTi record);
}