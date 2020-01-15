package com._520.crowdfunding.service.impl;

import com._520.crowdfunding.domain.TAdmin;
import com._520.crowdfunding.domain.TAdminExample;
import com._520.crowdfunding.domain.TPermission;
import com._520.crowdfunding.domain.TRole;
import com._520.crowdfunding.mapper.TAdminMapper;
import com._520.crowdfunding.mapper.TPermissionMapper;
import com._520.crowdfunding.mapper.TRoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *  加载用户信息，授予权限，角色
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private TAdminMapper adminMapper;

    @Autowired
    private TRoleMapper roleMapper;

    @Autowired
    private TPermissionMapper permissionMapper;

    private Logger logger = LoggerFactory.getLogger(UserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 查询用户
        TAdminExample example = new TAdminExample();
        example.createCriteria().andLoginacctEqualTo(username);
        List<TAdmin> admins = adminMapper.selectByExample(example);

        if (admins.isEmpty()){
            return null;
        }
        TAdmin user = admins.get(0);
        logger.info("用户 = {}", user.getLoginacct());
        // 查询用户具有的角色

        List<TRole> roles = roleMapper.listRoleByAdminId(user.getId());

        // 查询用户具有的权限
        List<TPermission> permissions = permissionMapper.listPermissionByAdminId(user.getId());

        // 存放所有的权限
        Set<GrantedAuthority> authorities = new HashSet<>();

        for (TRole role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }


        for (TPermission permission : permissions) {
            authorities.add(new SimpleGrantedAuthority(permission.getName()));
        }

        logger.info("角色 = {}", roles);
        logger.info("权限 = {}", permissions);
        logger.info("分配后的权限 = {}", authorities);
        return new User(user.getLoginacct(), user.getUserpswd(), authorities);
    }
}
