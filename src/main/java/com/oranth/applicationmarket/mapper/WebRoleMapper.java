package com.oranth.applicationmarket.mapper;

import com.oranth.applicationmarket.entity.WebRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色mapper层
 */
@Mapper
public interface WebRoleMapper {
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
    public Integer addWebRole(WebRole webRole);

    /**
     * 删除角色信息
     * @param roleId
     * @return
     */
    public Integer delWebRole(Integer roleId);

    /**
     * 修改角色信息
     * @param webRole
     * @return
     */
    public Integer editWebRole(WebRole webRole);
}
