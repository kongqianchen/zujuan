package com.zujuan.controller.protal;

import com.zujuan.common.ServerResponse;
import com.zujuan.pojo.ZhiShiDian;
import com.zujuan.service.IUserService;
import com.zujuan.service.IZhiShiDianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;


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
    public ServerResponse<List<Integer>> selectZhiShiDianAndChildrenById(Integer pointId) {
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
     * @param zhiShiDian
     * @return
     */
    @RequestMapping("updateZSD.do")
    @ResponseBody
    public ServerResponse updateZhiShiDian(ZhiShiDian zhiShiDian) {
        return iZhiShiDianService.updateZhiShiDian(zhiShiDian);
    }

    public ServerResponse delectZhiShiDianById(Integer pointId) {
        return iZhiShiDianService.delectZhiShiDianById(pointId);
    }
}
