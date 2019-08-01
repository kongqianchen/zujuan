package com.zujuan.service;

import com.zujuan.common.ServerResponse;
import com.zujuan.pojo.ZhiShiDian;

import java.util.List;
import java.util.Set;

/**
 * @Author Guojian Wang
 * @Date 2019/7/23 10:22
 */
public interface IZhiShiDianService {
    ServerResponse<ZhiShiDian> addZhiShiDian(String pointName);

    ServerResponse<ZhiShiDian> updateZhiShiDian(ZhiShiDian zhiShiDian);

    ServerResponse<List<ZhiShiDian>> getChildrenParallelZhiShiDian(Integer pointId);

    ServerResponse<Set<ZhiShiDian>> selectZhiShiDianAndChildrenById(Integer pointId);

    ServerResponse<List<String>> queryPointNamesByStr(String pointName);

    ServerResponse deleteZhiShiDianById(Integer pointId);

    ServerResponse<List<ZhiShiDian>> queryLonelyPoint();
}
