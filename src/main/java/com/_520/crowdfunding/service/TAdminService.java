package com._520.crowdfunding.service;

import com._520.crowdfunding.domain.TAdmin;
import com.github.pagehelper.PageInfo;

import java.util.List;
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

    /**
     *  添加用户
     * @param admin     添加的用户
     */
    void saveTAdmin(TAdmin admin);

    /**
     *  查询指定id的用户
     */
    TAdmin getTAdminById(Integer id);

    /**
     *  修改用户数据
     * @param admin     修改后的数据
     */
    void updateTAdmin(TAdmin admin);

    /**
     *  删除指定id的用户
     */
    void deleteById(Integer id);

    /**
     *  批量删除
     * @param idList    删除用户的id
     */
    void deleteBatch(List<Integer> idList);
}
