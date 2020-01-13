package com._520.crowdfunding.service.impl;

import com._520.crowdfunding.domain.*;
import com._520.crowdfunding.mapper.TAdminRoleMapper;
import com._520.crowdfunding.mapper.TRoleMapper;
import com._520.crowdfunding.mapper.TRolePermissionMapper;
import com._520.crowdfunding.service.TRoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TRoleServiceImpl implements TRoleService {

    @Autowired
    private TRoleMapper roleMapper;

    @Autowired
    private TAdminRoleMapper adminRoleMapper;

    @Autowired
    private TRolePermissionMapper rolePermissionMapper;

    @Override
    public PageInfo<TRole> listAllTRole(Map<String, Object> map) {
        String condition = (String) map.get("condition");
        TRoleExample example = new TRoleExample();
        // 如果有条件就进行模糊查询
        if (!"".equals(condition.trim())){
            example.createCriteria().andNameLike("%" + condition + "%");
        }
        List<TRole> tRoles = roleMapper.selectByExample(example);
        return new PageInfo<>(tRoles, 5);
    }

    @Override
    public Integer add(TRole role) {
        return roleMapper.insertSelective(role);
    }

    @Override
    public TRole getRoleById(Integer id) {
        TRoleKey key = new TRoleKey();
        key.setId(id);
        return roleMapper.selectByPrimaryKey(key);
    }

    @Override
    public Integer updateById(TRole role) {
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public Integer deleteById(Integer id) {
        TRoleKey key = new TRoleKey();
        key.setId(id);
        return roleMapper.deleteByPrimaryKey(key);
    }

    @Override
    public Integer deleteBatch(List<Integer> list) {
        TRoleExample example = new TRoleExample();
        example.createCriteria().andIdIn(list);
        return roleMapper.deleteByExample(example);
    }

    @Override
    public List<TRole> listAll() {
        return roleMapper.selectByExample(null);
    }

    @Override
    public List<Integer> getRoleIdByAdminId(Integer id) {

        TAdminRoleExample example = new TAdminRoleExample();
        example.createCriteria().andAdminidEqualTo(id);

        List<TAdminRole> tAdminRoles = adminRoleMapper.selectByExample(example);

        List<Integer> ids = new ArrayList<>();
        for (TAdminRole adminRole : tAdminRoles){
            ids.add(adminRole.getRoleid());
        }
        return ids;
    }

    @Override
    public Integer protectAdmainAndRole(Integer adminId, List<Integer> roleIds) {
        int count = 0;
        for (Integer roleId : roleIds) {
            TAdminRole adminRole = new TAdminRole(adminId, roleId);
            count += adminRoleMapper.insertSelective(adminRole);
        }
        return count;
    }

    @Override
    public Integer protectAdmainAndRoleByDelete(Integer adminId, List<Integer> roleIds) {
        int count = 0;

        for (Integer roleId : roleIds) {
            TAdminRoleExample example = new TAdminRoleExample();
            example.createCriteria().andAdminidEqualTo(adminId).andRoleidEqualTo(roleId);
            count += adminRoleMapper.deleteByExample(example);
        }
        return count;
    }

    @Override
    public Integer protectRoleAndPermission(Integer roleId, List<Integer> permissionIds) {
        int count = 0;
        // 删除角色的权限

        TRolePermissionExample example = new TRolePermissionExample();
        example.createCriteria().andRoleidEqualTo(roleId);
        rolePermissionMapper.deleteByExample(example);

        // 保存角色和对应的权限
        for (Integer permissionId : permissionIds) {
            TRolePermission rolePermission = new TRolePermission(roleId, permissionId);
            count += rolePermissionMapper.insertSelective(rolePermission);
        }
        return count;
    }

    @Override
    public List<Integer> getPermissionIdByRoleId(Integer roleId) {

        TRolePermissionExample example = new TRolePermissionExample();
        example.createCriteria().andRoleidEqualTo(roleId);

        List<TRolePermission> tRolePermissions = rolePermissionMapper.selectByExample(example);

        List<Integer> permissionIds = new ArrayList<>();
        for (TRolePermission tRolePermission : tRolePermissions) {
            permissionIds.add(tRolePermission.getPermissionid());
        }
        System.out.println(permissionIds);
        return permissionIds;
    }
}
