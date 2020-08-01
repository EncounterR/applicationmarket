package com.oranth.applicationmarket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.oranth.applicationmarket.entity.WebUser;

import java.util.List;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author linsiteng
 * @since 2020-04-21
 */
public interface IWebUserService {

    /**
     * 管理员登陆
     * @param userName
     * @param userPassword
     * @return
     */
    public WebUser login(String userName,String userPassword);

    /**
     * 根据id获取管理员信息
     * @param id
     * @return
     */
    public WebUser getWebUserById(Integer id);

    /**
     * 查询全部管理员信息
     * @return
     */
    public List<WebUser> allWebUser();

    /**
     * 添加管理员信息
     * @param webUser
     * @return
     */
    public Boolean addWebUser(WebUser webUser);

    /**
     * 删除管理员信息
     * @param id
     * @return
     */
    public Boolean delWebUser(Integer id);

    /**
     * 修改管理员信息
     * @param webUser
     * @return
     */
    public Boolean editWebUser(WebUser webUser);



}
