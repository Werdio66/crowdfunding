package com._520.crowdfunding.domain;

public class TRolePermission extends TRolePermissionKey {
    private Integer roleid;

    private Integer permissionid;

    public TRolePermission(Integer roleId, Integer permissionId) {
        this.roleid = roleId;
        this.permissionid = permissionId;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getPermissionid() {
        return permissionid;
    }

    public void setPermissionid(Integer permissionid) {
        this.permissionid = permissionid;
    }
}