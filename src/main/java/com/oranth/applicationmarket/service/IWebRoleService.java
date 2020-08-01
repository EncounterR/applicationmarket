package com.oranth.applicationmarket.service;

import com.oranth.applicationmarket.entity.WebRole;
import com.sun.org.apache.xpath.internal.operations.Bool;
import jdk.nashorn.internal.ir.BlockLexicalContext;

import java.util.List;

/**
 * 角色service层
 */
public interface IWebRoleService {
    /**
     * 获取全部角色信息
     * @return
     */
    public List<WebRole> getAll();

    /**
     * 根据角色id获取角色信息
     * @param roleId
     * @return
     */
    public WebRole getWebRoleByRoleId(Integer roleId);

    /**
     * 增加角色信息
     * @param webRole
     * @return
     */
    public Boolean addWebRole(WebRole webRole);

    /**
     * 删除角色信息
     * @param roleId
     * @return
     */
    public Boolean delWebRole(Integer roleId);

    /**
     * 修改角色信息
     * @param webRole
     * @return
     */
    public Boolean editWebRole(WebRole webRole);
}
