package com.zujuan.dao;

import com.zujuan.pojo.ST_ZSD;
import com.zujuan.pojo.ST_ZSDKey;

import java.util.List;

public interface ST_ZSDMapper {
    int deleteByPrimaryKey(ST_ZSDKey key);

    int insert(ST_ZSD record);

    int insertSelective(ST_ZSD record);

    ST_ZSD selectByPrimaryKey(ST_ZSDKey key);

    int updateByPrimaryKeySelective(ST_ZSD record);

    int updateByPrimaryKey(ST_ZSD record);

    List<ST_ZSD> queryAllST_ZSDByPointId(Integer pointId);
}