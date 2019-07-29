package com.zujuan.dao;

import com.zujuan.pojo.LongContent;

public interface LongContentMapper {
    int deleteByPrimaryKey(Integer zhizhenId);

    int insert(LongContent record);

    int insertSelective(LongContent record);

    LongContent selectByPrimaryKey(Integer zhizhenId);

    int updateByPrimaryKeySelective(LongContent record);

    int updateByPrimaryKeyWithBLOBs(LongContent record);
}