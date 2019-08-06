package com.zujuan.service.impl;

import com.zujuan.common.ServerResponse;
import com.zujuan.dao.JianSuoMapper;
import com.zujuan.dao.ZhiShiDianMapper;
import com.zujuan.pojo.JianSuo;
import com.zujuan.pojo.ZhiShiDian;
import com.zujuan.service.IJianSuoService;
import com.zujuan.service.IZhiShiDianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

    @Autowired
    ZhiShiDianMapper zhiShiDianMapper;

    /**
     * 新增检索
     *
     * @param jianSuo
     * @return
     */
    @Override
    public ServerResponse<JianSuo> addJianSuo(JianSuo jianSuo) {
        ZhiShiDian zhiShiDian = zhiShiDianMapper.selectByPrimaryKey(jianSuo.getPointId());
        int resultCount = jianSuoMapper.insert(jianSuo);
        if (resultCount <= 0 || zhiShiDian == null || zhiShiDian.getPointId() != 0) {
            return ServerResponse.createByErrorMessage("新增检索失败,非根知识点");
        }
        return ServerResponse.createBySuccess("新增检索成功", jianSuo);
    }

    /**
     * 更新检索
     *
     * @param jianSuo
     * @return
     */
    @Override
    public ServerResponse<JianSuo> updateJianSuo(JianSuo jianSuo) {
        int updateCount = jianSuoMapper.updateByPrimaryKeySelective(jianSuo);
        ZhiShiDian zhiShiDian = zhiShiDianMapper.selectByPrimaryKey(jianSuo.getPointId());
        if (updateCount <= 0 || zhiShiDian == null || zhiShiDian.getParentId() == null || zhiShiDian.getParentId() != 0) {
            return ServerResponse.createByErrorMessage("更新检索失败");
        }
        return ServerResponse.createBySuccess("更新检索成功", jianSuo);
    }

    /**
     * 删除检索
     *
     * @param jianSuoId
     * @return
     */
    @Override
    public ServerResponse deleteJianSuo(Integer jianSuoId) {
        int resultCount = jianSuoMapper.deleteByPrimaryKey(jianSuoId);
        if (resultCount <= 0) {
            return ServerResponse.createByErrorMessage("删除检索失败");
        }
        return ServerResponse.createBySuccessMessage("删除检索成功");
    }

    /**
     * 查找检索
     *
     * @param jianSuoId
     * @return
     */
    @Override
    public ServerResponse<JianSuo> queryJianSuoById(Integer jianSuoId) {
        JianSuo jianSuo = jianSuoMapper.selectByPrimaryKey(jianSuoId);
        if (jianSuo == null) {
            ServerResponse.createByErrorMessage("查找失败");
        }
        return ServerResponse.createBySuccess(jianSuo);
    }

    /**
     * 显示所有检索
     *
     * @return
     */
    @Override
    public ServerResponse<List<JianSuo>> queryAllJianSuo() {
        List<JianSuo> jianSuoList = jianSuoMapper.selectAll();
        if (CollectionUtils.isEmpty(jianSuoList)) {
            ServerResponse.createByErrorMessage("检索表为空");
        }
        return ServerResponse.createBySuccess(jianSuoList);
    }
}
