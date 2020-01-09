package com._520.crowdfunding.domain;

public class TPermissionMenu extends TPermissionMenuKey {
    private Integer menuid;

    private Integer permissionid;

    public Integer getMenuid() {
        return menuid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

    public Integer getPermissionid() {
        return permissionid;
    }

    public void setPermissionid(Integer permissionid) {
        this.permissionid = permissionid;
    }
}