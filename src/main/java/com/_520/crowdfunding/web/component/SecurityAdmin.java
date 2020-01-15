package com._520.crowdfunding.web.component;

import com._520.crowdfunding.domain.TAdmin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.Collection;

/**
 *  封装登录用户的所有信息
 */
public class SecurityAdmin extends User {

    private TAdmin admin;

    public SecurityAdmin(TAdmin admin, Collection<? extends GrantedAuthority> authorities) {
        super(admin.getLoginacct(), admin.getUserpswd(), authorities);
        this.admin = admin;
    }


    public TAdmin getAdmin() {
        return admin;
    }
}
