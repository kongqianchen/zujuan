package com.zujuan.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.zujuan.common.ServerResponse;
import com.zujuan.dao.ZhiShiDianMapper;
import com.zujuan.pojo.ZhiShiDian;
import com.zujuan.service.IZhiShiDianService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

/**
 * @Author Guojian Wang
 * @Date 2019/7/23 10:23
 */
@Service("iZhiShiDianSrevice")
public class IZhiShiDianServiceImpl implements IZhiShiDianService {
    private Logger logger = LoggerFactory.getLogger(IZhiShiDianServiceImpl.class);

    @Autowired
    private ZhiShiDianMapper zhiShiDianMapper;

    /**
     * 根据字符串匹配知识点
     *
     * @param pointName
     * @return
     */
    @Override
    public ServerResponse<List<String>> queryPointNamesByStr(String pointName) {
        return ServerResponse.createBySuccess();
    }

    /*public ServerResponse queryParentId(String parentName, String pointName) {
        Integer parentId = zhiShiDianMapper.queryParentId(parentName);
        if (parentId != null) {
            addZhiShiDian(pointName, parentId);
        }
        addZhiShiDian(pointName, 0);
        return ServerResponse.createBySuccess();
    }*/


    /**
     * 添加知识点
     * todo 权限
     *
     * @param pointName
     * @return
     */
    @Override
    public ServerResponse<ZhiShiDian> addZhiShiDian(String pointName) {
        if (StringUtils.isBlank(pointName)) {
            return ServerResponse.createByErrorMessage("添加知识点参数错误");
        }

        ZhiShiDian zhiShiDian = new ZhiShiDian();
        zhiShiDian.setPointName(pointName);
        zhiShiDian.setParentId(null);
        zhiShiDian.setStatusResult(false);
        int rowCount = zhiShiDianMapper.insert(zhiShiDian);
        if (rowCount <= 0) {
            return ServerResponse.createByErrorMessage("添加知识点成功");
        }
        return ServerResponse.createBySuccess("添加知识点失败", zhiShiDian);
    }

    /**
     * 更新知识点
     * todo 用户权限判断
     *
     * @param pointId
     * @param pointName
     * @return
     */
    @Override
    public ServerResponse updateZhiShiDian(Integer pointId, String pointName) {
        if (pointId == null || StringUtils.isBlank(pointName)) {
            return ServerResponse.createByErrorMessage("更新知识点参数错误");
        }
        ZhiShiDian zhiShiDian = new ZhiShiDian();
        zhiShiDian.setPointId(pointId);
        zhiShiDian.setPointName(pointName);

        int rowCount = zhiShiDianMapper.updateByPrimaryKeySelective(zhiShiDian);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("更新知识点名字成功");
        }
        return ServerResponse.createByErrorMessage("更新知识点名字失败");
    }

    /**
     * 删除知识点
     *
     * @param pointId
     * @return
     */
    @Override
    public ServerResponse delectZhiShiDianById(Integer pointId) {
        List<ZhiShiDian> zhiShiDianList = getChildrenParallelZhiShiDian(pointId).getData();

        // todo 查询该节点是否有父节点

        // todo 如果存在，则遍历将list,将list中的节点的父节点设置为当前节点的父节点

        // todo 如果没有,则设置为0
        return ServerResponse.createBySuccess();
    }

    /**
     * 获取知识点的子节点
     *
     * @param pointId
     * @return
     */
    @Override
    public ServerResponse<List<ZhiShiDian>> getChildrenParallelZhiShiDian(Integer pointId) {
        List<ZhiShiDian> zhiShiDianList = zhiShiDianMapper.selectZhiShiDinaChildrenByParentId(pointId);
        if (CollectionUtils.isEmpty(zhiShiDianList)) {
            logger.info("未找到当前分类的子分类");
        }
        return ServerResponse.createBySuccess(zhiShiDianList);
    }

    /**
     * 递归查询本节点的id及孩子节点的id
     *
     * @param pointId
     * @return
     */
    @Override
    public ServerResponse<List<Integer>> selectZhiShiDianAndChildrenById(Integer pointId) {
        Set<ZhiShiDian> zhiShiDianSet = Sets.newHashSet();
        findChildCategory(zhiShiDianSet, pointId);

        List<Integer> pointIdList = Lists.newArrayList();
        if (pointId != null) {
            for (ZhiShiDian zhiShiDian : zhiShiDianSet) {
                pointIdList.add(zhiShiDian.getPointId());
            }
        }
        return ServerResponse.createBySuccess(pointIdList);
    }

    /**
     * 递归算法，算出子节点
     *
     * @return
     */
    private Set<ZhiShiDian> findChildCategory(Set<ZhiShiDian> zhiShiDianSet, Integer pointId) {
        ZhiShiDian zhiShiDian = zhiShiDianMapper.selectByPrimaryKey(pointId);
        if (zhiShiDian != null) {
            zhiShiDianSet.add(zhiShiDian);
        }
        // 查找子节点
        List<ZhiShiDian> zhiShiDianList = zhiShiDianMapper.selectZhiShiDinaChildrenByParentId(pointId);
        for (ZhiShiDian zhiShiDianItem : zhiShiDianList) {
            findChildCategory(zhiShiDianSet, zhiShiDianItem.getPointId());
        }
        return zhiShiDianSet;
    }
}