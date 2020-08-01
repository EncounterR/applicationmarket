package com.oranth.applicationmarket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oranth.applicationmarket.entity.WebUser;
import com.oranth.applicationmarket.mapper.WebUserMapper;
import com.oranth.applicationmarket.service.IWebUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author linsiteng
 * @since 2020-04-21
 */
@Service
public class WebUserServiceImpl implements IWebUserService {

    @Autowired
    WebUserMapper webUserMapper;

    @Override
    public WebUser login(String userName, String userPassword) {
        WebUser webUser=webUserMapper.getWebUserByUserNameAndUserPassword(userName,userPassword);
        return webUser;
    }

    @Override
    public WebUser getWebUserById(Integer id) {
        return webUserMapper.getWebUserByUserId(id);
    }

    @Override
    public List<WebUser> allWebUser() {
        return webUserMapper.getAll();
    }

    @Override
    public Boolean addWebUser(WebUser webUser) {
        int row=webUserMapper.addWebUser(webUser);
        return (row == 1) ? true : false;
    }

    @Override
    public Boolean delWebUser(Integer id) {
        int row=webUserMapper.delWebUser(id);
        return (row == 1) ? true : false;
    }

    @Override
    public Boolean editWebUser(WebUser webUser) {
        int row=webUserMapper.editWebUser(webUser);
        return (row == 1) ? true : false;
    }
}
