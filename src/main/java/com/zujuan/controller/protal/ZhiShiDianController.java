package com.zujuan.controller.protal;

import com.zujuan.common.ServerResponse;
import com.zujuan.pojo.ZhiShiDian;
import com.zujuan.service.IUserService;
import com.zujuan.service.IZhiShiDianService;
import com.zujuan.vo.JianSuo_ZhiShiDianVo;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * @Author Guojian Wang
 * @Date 2019/7/23 12:54
 */
@Controller
@RequestMapping("/zhishidian/")
public class ZhiShiDianController {
    @Autowired
    private IUserService iUserService;

    @Autowired
    private IZhiShiDianService iZhiShiDianService;

    /**
     * 获取知识点的子节点
     *
     * @param pointId
     * @return
     */
    @RequestMapping("getChildren.do")
    @ResponseBody
    public ServerResponse<List<ZhiShiDian>> getChildrenParallelZhiShiDian(Integer pointId) {
        return iZhiShiDianService.getChildrenParallelZhiShiDian(pointId);
    }

    /**
     * 递归查询本节点的id及孩子节点的id
     *
     * @param pointId
     * @return
     */
    @RequestMapping("getAll.do")
    @ResponseBody
    public ServerResponse<Set<ZhiShiDian>> selectZhiShiDianAndChildrenById(Integer pointId) {
        return iZhiShiDianService.selectZhiShiDianAndChildrenById(pointId);
    }

    /**
     * 新增知识点
     *
     * @param pointName
     * @return
     */
    @RequestMapping("addZSD.do")
    @ResponseBody
    public ServerResponse<ZhiShiDian> addZhiShiDian(String pointName) {
        return iZhiShiDianService.addZhiShiDian(pointName);
    }

    /**
     * 更新知识点
     *
     * @param pointId
     * @param pointName
     * @return
     */
    @RequestMapping("updateZSD.do")
    @ResponseBody
    public ServerResponse updateZhiShiDian(Integer pointId, String pointName) {
        return iZhiShiDianService.updateZhiShiDian(pointId, pointName);
    }

    /**
     * 删除知识点
     *
     * @param pointId
     * @return
     */
    @RequestMapping("deleteZSD.do")
    @ResponseBody
    public ServerResponse deleteZhiShiDianById(Integer pointId) {
        return iZhiShiDianService.deleteZhiShiDianById(pointId);
    }

    /**
     * 模糊查询知识点
     *
     * @param pointName
     * @return
     */
    @RequestMapping("query.do")
    @ResponseBody
    public ServerResponse<List<String>> queryPointNamesByStr(String pointName) {
        return iZhiShiDianService.queryPointNamesByStr(pointName);
    }

    /**
     * 查询孤儿节点
     *
     * @return
     */
    @RequestMapping("queryOrphan.do")
    @ResponseBody
    public ServerResponse<List<ZhiShiDian>> queryLonelyPoint() {
        return iZhiShiDianService.queryLonelyPoint();
    }

    /**
     * 修改父节点操作
     *
     * @param pointId
     * @param parentId
     * @return
     */
    @RequestMapping("changeParentId.do")
    @ResponseBody
    public ServerResponse<ZhiShiDian> changeParentId(Integer pointId, Integer parentId) {
        return iZhiShiDianService.changeParentId(pointId, parentId);
    }

    /**
     * 修改一组节点操作
     *
     * @param point_Parent
     * @return
     */
    @RequestMapping("changeMulParentId.do")
    @ResponseBody
    public ServerResponse<List<ZhiShiDian>> changeParentId(String point_Parent) {
        JSONArray jsonArray = JSONArray.fromObject(point_Parent);
        List<ZhiShiDian> zhiShiDianList = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            Integer pointId = jsonArray.getJSONObject(i).getInt("pointId");
            Integer parentId = jsonArray.getJSONObject(i).getInt("parentId");
            zhiShiDianList.add(changeParentId(pointId, parentId).getData());
        }
        return ServerResponse.createBySuccess("修改父节点成功", zhiShiDianList);
    }

    /**
     * 查询所有知识点
     *
     * @return
     */
    @RequestMapping("queryAllZSD.do")
    @ResponseBody
    public ServerResponse<List<ZhiShiDian>> queryAll() {
        return iZhiShiDianService.queryAll();
    }

    /**
     * 查询没有检索的一级知识点
     *
     * @return
     */
    @RequestMapping("queryZSDWJS.do")
    @ResponseBody
    ServerResponse<List<ZhiShiDian>> queryZhiShiDianWithoutJianSuo() {
        return iZhiShiDianService.queryZhiShiDianWithoutJianSuo();
    }

    /**
     * 查询有检索的一级知识点
     *
     * @return
     */
    @RequestMapping("queryZSDJS.do")
    @ResponseBody
    ServerResponse<List<JianSuo_ZhiShiDianVo>> queryZhiShiDianWithJianSuo() {
        return iZhiShiDianService.queryZhiShiDianWithJianSuo();
    }
}
