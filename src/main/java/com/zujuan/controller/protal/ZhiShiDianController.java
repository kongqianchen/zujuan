package com.zujuan.controller.protal;

import com.zujuan.service.IUserService;
import com.zujuan.service.IZhiShiDianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @Author Guojian Wang
 * @Date 2019/7/23 12:54
 */
@Controller
@RequestMapping("/manage/zhishidian")
public class ZhiShiDianController {
    @Autowired
    private IUserService iUserService;

    @Autowired
    private IZhiShiDianService iZhiShiDianService;


}
