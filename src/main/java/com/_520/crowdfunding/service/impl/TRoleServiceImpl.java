package com._520.crowdfunding.service.impl;

import com._520.crowdfunding.domain.TRole;
import com._520.crowdfunding.domain.TRoleExample;
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
        List<TRole> tRoles = roleMapper.selectByExample(example);
        return new PageInfo<>(tRoles, 5);
    }
}
