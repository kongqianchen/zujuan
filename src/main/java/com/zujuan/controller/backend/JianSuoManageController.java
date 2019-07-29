package com.zujuan.controller.backend;

import com.zujuan.common.ServerResponse;
import com.zujuan.pojo.JianSuo;
import com.zujuan.service.IJianSuoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Guojian Wang
 * @version 1.0
 * @date 2019/7/30 - 0:02
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
@Controller
@RequestMapping("/manage")
public class JianSuoManageController {
    @Autowired
    IJianSuoService iJianSuoService;

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
    @RequestMapping(value = "/addJianSuo.do", method = RequestMethod.POST)
    public ServerResponse<JianSuo> addJianSuo(String dengji, String xueke, String nianji,
                                              String zhangjie, String pointName) {
        return iJianSuoService.addJianSuo(dengji, xueke, nianji, zhangjie, pointName);
    }

    /**
     * 更新检索
     *
     * @param jianSuo
     * @return
     */
    public ServerResponse<JianSuo> updateJianSuo(JianSuo jianSuo) {
        return iJianSuoService.updateJianSuo(jianSuo);
    }
}
