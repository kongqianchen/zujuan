package com.zujuan.service.impl;

import com.zujuan.common.ServerResponse;
import com.zujuan.dao.JianSuoMapper;
import com.zujuan.pojo.JianSuo;
import com.zujuan.service.IJianSuoService;
import com.zujuan.service.IZhiShiDianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Guojian Wang
 * @version 1.0
 * @date 2019/7/29 - 23:22
 * @since 1.0
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃神兽保佑, 永无BUG!
 * 　　　　 ┃　　　┃Code is far away from bug with the animal protecting
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━感觉萌萌哒━━━━━━
 */
@Service
public class IJianSuoServiceImpl implements IJianSuoService {

    @Autowired
    JianSuoMapper jianSuoMapper;

    @Autowired
    IZhiShiDianService iZhiShiDianService;

    /**
     * 新增检索
     *
     * @param dengji
     * @param xueke
     * @param nianji
     * @param zhangjie
     * @param pointName
     * @return
     */
    @Override
    public ServerResponse<JianSuo> addJianSuo(String dengji, String xueke, String nianji,
                                              String zhangjie, String pointName) {
        int pointId = iZhiShiDianService.addZhiShiDian(pointName).getData().getPointId();

        JianSuo jianSuo = new JianSuo();
        jianSuo.setDengji(dengji);
        jianSuo.setXueke(xueke);
        jianSuo.setNianji(nianji);
        jianSuo.setZhangjie(zhangjie);
        jianSuo.setPointName(pointName);
        jianSuo.setPointId(pointId);
        int resultCount = jianSuoMapper.insert(jianSuo);
        if (resultCount <= 0) {
            return ServerResponse.createByErrorMessage("添加检索失败");
        }
        return ServerResponse.createBySuccess("添加检索成功", jianSuo);
    }

    /**
     * 更新检索
     *
     * @param jianSuo
     * @return
     */
    @Override
    public ServerResponse<JianSuo> updateJianSuo(JianSuo jianSuo) {
        JianSuo updateJianSuo = new JianSuo();
        updateJianSuo.setDengji(jianSuo.getDengji());
        updateJianSuo.setNianji(jianSuo.getNianji());
        updateJianSuo.setPointId(jianSuo.getPointId());
        updateJianSuo.setXueke(jianSuo.getXueke());
        updateJianSuo.setZhangjie(jianSuo.getZhangjie());
        int updateCount = jianSuoMapper.updateByPrimaryKeySelective(updateJianSuo);
        iZhiShiDianService.updateZhiShiDian(jianSuo.getPointId(), jianSuo.getPointName());
        if (updateCount <= 0) {
            return ServerResponse.createByErrorMessage("更新检索失败");
        }
        return ServerResponse.createBySuccess("更新检索成功", jianSuo);
    }

    public ServerResponse<List<JianSuo>> queryJianSuo()
}
