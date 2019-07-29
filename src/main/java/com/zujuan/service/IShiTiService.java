package com.zujuan.service;

import com.zujuan.common.ServerResponse;
import com.zujuan.pojo.ShiTi;

/**
 * @Author Guojian Wang
 * @Date 2019/7/17 12:56
 */
public interface IShiTiService {
    ServerResponse<ShiTi> submit(ShiTi shiTi);


}
