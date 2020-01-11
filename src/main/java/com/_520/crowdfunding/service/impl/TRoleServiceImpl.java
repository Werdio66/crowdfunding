package com._520.crowdfunding.service.impl;

import com._520.crowdfunding.domain.TRole;
import com._520.crowdfunding.domain.TRoleExample;
import com._520.crowdfunding.domain.TRoleKey;
import com._520.crowdfunding.mapper.TRoleMapper;
import com._520.crowdfunding.service.TRoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TRoleServiceImpl implements TRoleService {

    @Autowired
    private TRoleMapper roleMapper;

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
}
