package com.zujuan.service;

import com.zujuan.common.ServerResponse;
import com.zujuan.pojo.JianSuo;

import java.util.List;

/**
 * @author Guojian Wang
 * @version 1.0
 * @date 2019/7/29 - 23:21
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
public interface IJianSuoService {
    ServerResponse<JianSuo> addJianSuo(JianSuo jianSuo);

    ServerResponse<JianSuo> updateJianSuo(JianSuo jianSuo);

    ServerResponse deleteJianSuo(Integer jianSuoId);

    ServerResponse<List<JianSuo>> queryAllJianSuo();

    ServerResponse<JianSuo> queryJianSuoById(Integer jianSuoId);
}
