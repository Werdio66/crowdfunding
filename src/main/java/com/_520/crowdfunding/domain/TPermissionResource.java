package com._520.crowdfunding.domain;

public class TPermissionResource extends TPermissionResourceKey {
    private Integer resourceid;

    private Integer permissionid;

    public Integer getResourceid() {
        return resourceid;
    }

    public void setResourceid(Integer resourceid) {
        this.resourceid = resourceid;
    }

    public Integer getPermissionid() {
        return permissionid;
    }

    public void setPermissionid(Integer permissionid) {
        this.permissionid = permissionid;
    }
}