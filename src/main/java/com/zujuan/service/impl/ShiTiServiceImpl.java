package com.zujuan.service.impl;

import com.zujuan.common.ServerResponse;
import com.zujuan.dao.ShiTiMapper;
import com.zujuan.pojo.ShiTi;
import com.zujuan.pojo.ZhiShiDian;
import com.zujuan.service.IShiTiService;
import com.zujuan.vo.ST_ZSDVo;
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

    /*@Override
    public ServerResponse<ST_ZSDVo> add(ST_ZSDVo st_zsdVo) {
        ShiTi shiTi = new ShiTi();
        ZhiShiDian zhiShiDian = new ZhiShiDian();

        shiTi.setSource(st_zsdVo.getSource());
        shiTi.setCity(st_zsdVo.getCity());
        shiTi.setProblem(st_zsdVo.getProblem());
        shiTi.setAnswer(st_zsdVo.getAnswer());
        shiTi.setAnalysis(st_zsdVo.getAnalysis());
        shiTi.setNandu(st_zsdVo.getNandu());
        shiTi.setTilei(st_zsdVo.getTilei());
        shiTi.setTixing(st_zsdVo.getTixing());
        shiTi.setReferenceTime(0);
        shiTi.setYears(st_zsdVo.getYears());
        shiTiMapper.insert(shiTi);
    }*/
}
