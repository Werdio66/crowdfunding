package com._520.crowdfunding.service;

import com._520.crowdfunding.domain.TRole;
import com.github.pagehelper.PageInfo;
import java.util.Map;

public interface TRoleService {
    /**
     *  查询所有的角色
     * @param map       查询条件
     * @return          分页的结果
     */
    PageInfo<TRole> listAllTRole(Map<String, Object> map);

    Integer add(TRole role);
}
