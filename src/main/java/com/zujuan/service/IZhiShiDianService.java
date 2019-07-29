package com.zujuan.service;

import com.zujuan.common.ServerResponse;
import com.zujuan.pojo.ZhiShiDian;

import java.util.List;

/**
 * @Author Guojian Wang
 * @Date 2019/7/23 10:22
 */
public interface IZhiShiDianService {
    ServerResponse addZhiShiDian(String pointName, Integer parentId);

    ServerResponse updateZhiShiDian(Integer pointId, String pointName);

    ServerResponse<List<ZhiShiDian>> getChildrenParallelZhiShiDian(Integer pointId);

    ServerResponse<List<Integer>> selectZhiShiDianAndChildrenById(Integer pointId);

    ServerResponse delectZhiShiDianById(Integer pointId);

    ServerResponse<List<String>> queryPointNamesByStr(String pointName);
}
