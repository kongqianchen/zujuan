package com.zujuan.service.impl;

import com.google.common.collect.Sets;
import com.zujuan.common.ServerResponse;
import com.zujuan.dao.ZhiShiDianMapper;
import com.zujuan.pojo.JianSuo;
import com.zujuan.pojo.ST_ZSD;
import com.zujuan.pojo.ST_ZSDKey;
import com.zujuan.pojo.ZhiShiDian;
import com.zujuan.service.IJianSuoService;
import com.zujuan.service.IST_ZSDService;
import com.zujuan.service.IZhiShiDianService;
import com.zujuan.vo.JianSuo_ZhiShiDianVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
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

    @Autowired
    private IST_ZSDService ist_zsdService;

    @Autowired
    private IJianSuoService iJianSuoService;

    /**
     * 根据字符串匹配知识点
     *
     * @param pointName
     * @return
     */
    @Override
    public ServerResponse<List<String>> queryPointNamesByStr(String pointName) {
        String name = pointName + '%';
        List<String> pointNames = zhiShiDianMapper.queryPointNamesByStr(name);
        return ServerResponse.createBySuccess(pointNames);
    }

    /**
     * 查询孤儿节点
     *
     * @return
     */
    @Override
    public ServerResponse<List<ZhiShiDian>> queryLonelyPoint() {
        List<ZhiShiDian> zhiShiDianList = zhiShiDianMapper.queryPointByNullParentId();
        if (CollectionUtils.isEmpty(zhiShiDianList)) {
            return ServerResponse.createByErrorMessage("没有孤儿节点");
        }
        return ServerResponse.createBySuccess(zhiShiDianList);
    }

    /**
     * 修改父节点
     *
     * @param pointId
     * @param parentId
     * @return
     */
    @Override
    public ServerResponse<ZhiShiDian> changeParentId(Integer pointId, Integer parentId) {
        ZhiShiDian child = zhiShiDianMapper.selectByPrimaryKey(pointId);
        if (child == null) {
            return ServerResponse.createByErrorMessage("当前知识点不存在在");
        }
        child.setParentId(parentId);
        int resultCount = zhiShiDianMapper.updateByPrimaryKeySelective(child);
        if (resultCount <= 0) {
            ServerResponse.createByErrorMessage("修改父节点失败");
        }
        return ServerResponse.createBySuccess("修改父节点成功", child);
    }

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
        zhiShiDian.setStatusReview(0);
        int rowCount = zhiShiDianMapper.insert(zhiShiDian);
        if (rowCount <= 0) {
            return ServerResponse.createByErrorMessage("添加知识点失败");
        }
        return ServerResponse.createBySuccess("添加知识点成功", zhiShiDian);
    }

    /**
     * 更新知识点
     *
     * @param pointId
     * @param pointName
     * @return
     */
    @Override
    public ServerResponse<ZhiShiDian> updateZhiShiDian(Integer pointId, String pointName) {
        ZhiShiDian zhiShiDian = zhiShiDianMapper.selectByPrimaryKey(pointId);
        if (zhiShiDian == null) {
            return ServerResponse.createByErrorMessage("更新知识点名字失败");
        }
        zhiShiDian.setPointName(pointName);
        int rowCount = zhiShiDianMapper.updateByPrimaryKeySelective(zhiShiDian);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("更新知识点名字成功", zhiShiDian);
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
    public ServerResponse deleteZhiShiDianById(Integer pointId) {
        ZhiShiDian zhiShiDian = zhiShiDianMapper.selectByPrimaryKey(pointId);
        if (zhiShiDian == null) {
            return ServerResponse.createByErrorMessage("删除知识点失败");
        }
        Integer parentId = zhiShiDian.getParentId();
        if (parentId == null) {
            return ServerResponse.createByErrorMessage("该知识点不允许删除");
        }
        //如果有试题与该知识点有联系，把关联表的知识点id修改为父节点的id，如果父节点为空，则不允许删除该知识点。
        // 需要修改的item
        List<ST_ZSD> st_zsdList1 = ist_zsdService.queryAllST_ZSDByPointId(pointId).getData();
        // pointId==parentId的item
        List<ST_ZSD> st_zsdList2 = ist_zsdService.queryAllST_ZSDByPointId(parentId).getData();
        if (CollectionUtils.isEmpty(st_zsdList2)) {
            for (ST_ZSD st_zsd : st_zsdList1) {
                st_zsd.setPointId(parentId);
                ist_zsdService.updateST_ZSD(st_zsd);
            }
        } else {
            for (ST_ZSD st_zsd1 : st_zsdList1) {
                for (ST_ZSD st_zsd2 : st_zsdList2) {
                    if (st_zsd1.getShitiId().equals(st_zsd2.getShitiId())) {
                        ist_zsdService.deleteST_ZSD(new ST_ZSDKey(parentId, st_zsd2.getShitiId()));
                    }
                }
                st_zsd1.setPointId(parentId);
                ist_zsdService.updateST_ZSD(st_zsd1);
            }
        }
        // 集合为空直接删除
        List<ZhiShiDian> zhiShiDianList = getChildrenParallelZhiShiDian(pointId).getData();
        int resultCount = zhiShiDianMapper.deleteByPrimaryKey(pointId);
        if (CollectionUtils.isEmpty(zhiShiDianList)) {
            if (resultCount <= 0) {
                return ServerResponse.createByErrorMessage("删除知识点失败");
            }
            return ServerResponse.createBySuccessMessage("删除知识点成功");
        }

        for (ZhiShiDian zhiShiDianItem : zhiShiDianList) {
            zhiShiDianItem.setParentId(parentId);
            zhiShiDianMapper.updateByPrimaryKeySelective(zhiShiDianItem);
        }
        return ServerResponse.createBySuccessMessage("删除知识点成功");
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
    public ServerResponse<Set<ZhiShiDian>> selectZhiShiDianAndChildrenById(Integer pointId) {
        Set<ZhiShiDian> zhiShiDianSet = Sets.newHashSet();
        findChildCategory(zhiShiDianSet, pointId);
        if (CollectionUtils.isEmpty(zhiShiDianSet)) {
            logger.info("未找到当前分类的子分类");
        }
        return ServerResponse.createBySuccess(zhiShiDianSet);
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

    /**
     * 查询所有知识点
     *
     * @return
     */
    @Override
    public ServerResponse<List<ZhiShiDian>> queryAll() {
        List<ZhiShiDian> list = zhiShiDianMapper.queryAll();
        return ServerResponse.createBySuccess(list);
    }

    /**
     * 查询没有检索的一级知识点
     *
     * @return
     */
    @Override
    public ServerResponse<List<ZhiShiDian>> queryZhiShiDianWithoutJianSuo() {
        List<ZhiShiDian> zhiShiDianList = zhiShiDianMapper.queryZhiShiDianWithoutJianSuo();
        if (CollectionUtils.isEmpty(zhiShiDianList)) {
            ServerResponse.createByErrorMessage("未查询到没有检索的一级知识点");
        }
        return ServerResponse.createBySuccess(zhiShiDianList);
    }

    /**
     * 查询有检索的一级知识点
     *
     * @return
     */
    @Override
    public ServerResponse<List<JianSuo_ZhiShiDianVo>> queryZhiShiDianWithJianSuo() {
        List<JianSuo_ZhiShiDianVo> jianSuo_zhiShiDianList = new ArrayList<>();
        List<JianSuo> jianSuoList = iJianSuoService.queryAllJianSuo().getData();
        for (JianSuo jianSuo : jianSuoList) {
            ZhiShiDian zhiShiDian = zhiShiDianMapper.selectByPrimaryKey(jianSuo.getPointId());
            jianSuo_zhiShiDianList.add(new JianSuo_ZhiShiDianVo(
                    jianSuo.getJiansuoId(),
                    jianSuo.getDengji(),
                    jianSuo.getXueke(),
                    jianSuo.getNianji(),
                    jianSuo.getZhangjie(),
                    jianSuo.getPointId(),
                    (zhiShiDian == null) ? "" : zhiShiDian.getPointName()));
        }
        return ServerResponse.createBySuccess(jianSuo_zhiShiDianList);
    }
}
