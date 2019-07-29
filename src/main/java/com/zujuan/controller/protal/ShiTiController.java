package com.zujuan.controller.protal;

import com.zujuan.common.ServerResponse;
import com.zujuan.pojo.ShiTi;
import com.zujuan.service.IShiTiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author Guojian Wang
 * @Date 2019/7/17 12:56
 */
@Controller
@RequestMapping("/shiti/")
public class ShiTiController {
    @Autowired
    private IShiTiService iShiTiService;

    @RequestMapping(value = "submit.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<ShiTi> submit(ShiTi shiTi) {
        return iShiTiService.submit(shiTi);
    }
}
