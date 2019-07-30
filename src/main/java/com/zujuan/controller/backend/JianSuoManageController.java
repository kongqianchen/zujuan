package com.zujuan.controller.backend;

import com.zujuan.common.ServerResponse;
import com.zujuan.pojo.JianSuo;
import com.zujuan.service.IJianSuoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

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
@RequestMapping("/manage/")
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
    @RequestMapping(value = "addJianSuo.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<JianSuo> addJianSuo(JianSuo jianSuo) {
        return iJianSuoService.addJianSuo(jianSuo);
    }

    /**
     * 更新检索
     *
     * @param jianSuo
     * @return
     */
    @RequestMapping(value = "updateJianSuo.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<JianSuo> updateJianSuo(JianSuo jianSuo) {
        return iJianSuoService.updateJianSuo(jianSuo);
    }

    /**
     * 删除检索
     *
     * @param jianSuoId
     * @return
     */
    @RequestMapping("deleteJianSuo.do")
    @ResponseBody
    public ServerResponse deleteJianSuo(Integer jianSuoId) {
        return iJianSuoService.deleteJianSuo(jianSuoId);
    }
}
