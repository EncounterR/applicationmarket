package com.oranth.applicationmarket.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 角色表
 */
public class WebRole implements Serializable {
    private Integer role_id;//角色编号
    private String role_name;//角色名称
    private String role_desc;//角色详情
    private Integer role_flag;//角色状态 1正常,0停用
    private List<WebPermissions> webPermissionsList;//角色拥有的权限

    public WebRole(Integer role_id, String role_name, String role_desc, Integer role_flag, List<WebPermissions> webPermissionsList) {
        this.role_id = role_id;
        this.role_name = role_name;
        this.role_desc = role_desc;
        this.role_flag = role_flag;
        this.webPermissionsList = webPermissionsList;
    }

    public WebRole(String role_name, String role_desc, Integer role_flag, List<WebPermissions> webPermissionsList) {
        this.role_name = role_name;
        this.role_desc = role_desc;
        this.role_flag = role_flag;
        this.webPermissionsList = webPermissionsList;
    }

    public WebRole() {
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRole_desc() {
        return role_desc;
    }

    public void setRole_desc(String role_desc) {
        this.role_desc = role_desc;
    }

    public Integer getRole_flag() {
        return role_flag;
    }

    public void setRole_flag(Integer role_flag) {
        this.role_flag = role_flag;
    }

    public List<WebPermissions> getWebPermissionsList() {
        return webPermissionsList;
    }

    public void setWebPermissionsList(List<WebPermissions> webPermissionsList) {
        this.webPermissionsList = webPermissionsList;
    }
}
