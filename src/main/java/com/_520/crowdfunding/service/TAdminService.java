package com._520.crowdfunding.service;

import com._520.crowdfunding.domain.TAdmin;
import com.github.pagehelper.PageInfo;
import java.util.Map;

public interface TAdminService {

    /**
     *  判断登录是否成功
     * @param map   登录信息
     * @return      当前登录用户
     */
    TAdmin checkLogin(Map<String, Object> map);

    /**
     *  查询用户数据
     * @param map   查询条件
     * @return      分页显示用户
     */
    PageInfo<TAdmin> listAdminPage(Map<String, Object> map);
}
