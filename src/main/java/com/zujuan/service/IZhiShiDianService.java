package com.zujuan.service;

import com.zujuan.common.ServerResponse;
import com.zujuan.pojo.ZhiShiDian;
import com.zujuan.vo.JianSuo_ZhiShiDianVo;

import java.util.List;
import java.util.Set;

/**
 * @Author Guojian Wang
 * @Date 2019/7/23 10:22
 */
public interface IZhiShiDianService {
    ServerResponse<ZhiShiDian> addZhiShiDian(String pointName);

    ServerResponse<ZhiShiDian> updateZhiShiDian(Integer pointId, String pointName);

    ServerResponse<List<ZhiShiDian>> getChildrenParallelZhiShiDian(Integer pointId);

    ServerResponse<Set<ZhiShiDian>> selectZhiShiDianAndChildrenById(Integer pointId);

    ServerResponse<List<String>> queryPointNamesByStr(String pointName);

    ServerResponse deleteZhiShiDianById(Integer pointId);

    ServerResponse<List<ZhiShiDian>> queryLonelyPoint();

    ServerResponse<ZhiShiDian> changeParentId(Integer pointId, Integer parentId);

    ServerResponse<List<ZhiShiDian>> queryAll();

    ServerResponse<List<ZhiShiDian>> queryZhiShiDianWithoutJianSuo();

    ServerResponse<List<JianSuo_ZhiShiDianVo>> queryZhiShiDianWithJianSuo();
}
