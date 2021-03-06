package com._520.crowdfunding.domain;

public class TAdminRole extends TAdminRoleKey {
    private Integer adminid;

    private Integer roleid;

    public TAdminRole(){}

    public TAdminRole(Integer adminid, Integer roleid) {
        this.adminid = adminid;
        this.roleid = roleid;
    }

    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }
}