package com.zujuan.dao;

import com.zujuan.pojo.ZhiShiDian;

import java.util.List;

public interface ZhiShiDianMapper {
    int deleteByPrimaryKey(Integer pointId);

    int insert(ZhiShiDian record);

    int insertSelective(ZhiShiDian record);

    ZhiShiDian selectByPrimaryKey(Integer pointId);

    int updateByPrimaryKeySelective(ZhiShiDian record);

    int updateByPrimaryKey(ZhiShiDian record);

    List<ZhiShiDian> selectZhiShiDinaChildrenByParentId(Integer parent_id);

    List<String> queryPointNamesByStr(String pointName);

    List<ZhiShiDian> queryPointByNullParentId();
}