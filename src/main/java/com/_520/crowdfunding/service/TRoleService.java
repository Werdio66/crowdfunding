package com._520.crowdfunding.service;

import com._520.crowdfunding.domain.TRole;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface TRoleService {
    /**
     *  查询所有的角色
     * @param map       查询条件
     * @return          分页的结果
     */
    PageInfo<TRole> listAllTRole(Map<String, Object> map);

    /**
     *  增加角色
     */
    Integer add(TRole role);

    TRole getRoleById(Integer id);

    /**
     *  修改指定id的角色
     */
    Integer updateById(TRole role);

    /**
     *  删除指定id的角色
     */
    Integer deleteById(Integer id);

    /**
     *  批量删除
     * @param list      要删除的所有id
     */
    Integer deleteBatch(List<Integer> list);

    /**
     *  查询所有角色
     */
    List<TRole> listAll();

    /**
     *  查找出指定id用户已分配的角色id
     */
    List<Integer> getRoleIdByAdminId(Integer id);

    /**
     *  为用户分配角色，维护中间表
     * @param adminId        用户id
     * @param roleIds       分配角色的id
     */
    Integer protectAdmainAndRole(Integer adminId, List<Integer> roleIds);

    /**
     *  为用户取消分配角色
     * @param adminId       用户id
     * @param roleIds       取消分配角色的id
     */
    Integer protectAdmainAndRoleByDelete(Integer adminId, List<Integer> roleIds);
}
