package com.oranth.applicationmarket.service.impl;

import com.oranth.applicationmarket.entity.WebRole;
import com.oranth.applicationmarket.mapper.WebRoleMapper;
import com.oranth.applicationmarket.service.IWebRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebRoleServiceImpl implements IWebRoleService {

    @Autowired
    private WebRoleMapper webRoleMapper;

    @Override
    public List<WebRole> getAll() {
        return webRoleMapper.getAll();
    }

    @Override
    public WebRole getWebRoleByRoleId(Integer roleId) {
        return webRoleMapper.getWebRoleByRoleId(roleId);
    }

    @Override
    public Boolean addWebRole(WebRole webRole) {
        int row=webRoleMapper.addWebRole(webRole);
        return (row>0)?true:false;
    }

    @Override
    public Boolean delWebRole(Integer roleId) {
        int row=webRoleMapper.delWebRole(roleId);
        return (row>0)?true:false;
    }

    @Override
    public Boolean editWebRole(WebRole webRole) {
        int row=webRoleMapper.editWebRole(webRole);
        return (row>0)?true:false;
    }
}
