package com._520.crowdfunding.service.impl;

import com._520.crowdfunding.domain.TPermission;
import com._520.crowdfunding.domain.TPermissionKey;
import com._520.crowdfunding.mapper.TPermissionMapper;
import com._520.crowdfunding.service.TPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TPermissionServiceImpl implements TPermissionService {

    @Autowired
    private TPermissionMapper permissionMapper;

    @Override
    public List<TPermission> listAll() {
        return permissionMapper.selectByExample(null);
    }

    @Override
    public Integer addPermissionById(TPermission permission) {
        return permissionMapper.insertSelective(permission);
    }

    @Override
    public TPermission getPermissionById(Integer id) {
        TPermissionKey key = new TPermissionKey();
        key.setId(id);
        return permissionMapper.selectByPrimaryKey(key);
    }

    @Override
    public Integer updatePermission(TPermission permission) {
        return permissionMapper.updateByPrimaryKeySelective(permission);
    }

    @Override
    public Integer deleteById(Integer id) {
        TPermissionKey key = new TPermissionKey();
        key.setId(id);
        return permissionMapper.deleteByPrimaryKey(key);
    }
}
