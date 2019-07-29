package com.zujuan.dao;

import com.zujuan.pojo.JianSuo;

public interface JianSuoMapper {
    int deleteByPrimaryKey(Integer jiansuoId);

    int insert(JianSuo record);

    int insertSelective(JianSuo record);

    JianSuo selectByPrimaryKey(Integer jiansuoId);

    int updateByPrimaryKeySelective(JianSuo record);

    int updateByPrimaryKey(JianSuo record);
}