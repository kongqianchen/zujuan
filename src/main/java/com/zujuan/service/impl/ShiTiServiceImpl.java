package com.zujuan.service.impl;

import com.zujuan.common.ServerResponse;
import com.zujuan.dao.ShiTiMapper;
import com.zujuan.pojo.ShiTi;
import com.zujuan.service.IShiTiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Guojian Wang
 * @Date 2019/7/17 13:07
 */
@Service("iShiTiService")
public class ShiTiServiceImpl implements IShiTiService {
    private static final Logger logger = LoggerFactory.getLogger(ShiTiServiceImpl.class);

    @Autowired
    private ShiTiMapper shiTiMapper;

    @Override
    public ServerResponse<ShiTi> submit(ShiTi shiTi) {
        int resultCount = shiTiMapper.insert(shiTi);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("上传失败！");
        }
        return ServerResponse.createBySuccess("上传成功", shiTi);
    }
}
