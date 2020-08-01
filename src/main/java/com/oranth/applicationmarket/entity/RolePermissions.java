package com.oranth.applicationmarket.entity;

import java.io.Serializable;

/**
 * 角色，权限中间表，来维护角色所拥有的权限
 */
public class RolePermissions implements Serializable {
    /**
     * 角色id
     */
    private Integer role_id;
    /**
     * 权限id
     */
    private Integer permissionsId;

    public RolePermissions(Integer role_id, Integer permissionsId) {
        this.role_id = role_id;
        this.permissionsId = permissionsId;
    }

    public RolePermissions() {
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public Integer getPermissionsId() {
        return permissionsId;
    }

    public void setPermissionsId(Integer permissionsId) {
        this.permissionsId = permissionsId;
    }
}
