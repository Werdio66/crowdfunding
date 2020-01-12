package com._520.crowdfunding.service;

import com._520.crowdfunding.domain.TPermission;

import java.util.List;

public interface TPermissionService {

    List<TPermission> listAll();

    Integer addPermissionById(TPermission permission);

    TPermission getPermissionById(Integer id);

    Integer updatePermission(TPermission permission);

    Integer deleteById(Integer id);
}
