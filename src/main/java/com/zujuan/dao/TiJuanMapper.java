package com.zujuan.dao;

import com.zujuan.pojo.TiJuan;
import com.zujuan.pojo.TiJuanKey;

public interface TiJuanMapper {
    int deleteByPrimaryKey(TiJuanKey key);

    int insert(TiJuan record);

    int insertSelective(TiJuan record);

    TiJuan selectByPrimaryKey(TiJuanKey key);

    int updateByPrimaryKeySelective(TiJuan record);

    int updateByPrimaryKey(TiJuan record);
}