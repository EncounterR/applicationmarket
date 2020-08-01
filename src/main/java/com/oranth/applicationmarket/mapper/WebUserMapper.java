package com.oranth.applicationmarket.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oranth.applicationmarket.entity.WebUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 管理员表 Mapper 接口
 * </p>
 *
 * @author linsiteng
 * @since 2020-04-21
 */
@Mapper
public interface WebUserMapper{
    /**
     * 获取全部管理员信息
     * @return 返回一个webuser list集合
     */
    public List<WebUser> getAll();

    /**
     * 通过用户名查询用户信息
     * @param userName
     * @return
     */
    public WebUser getWebUserByUserName(String userName);

    /**
     * 根据账号和密码查询管理员信息
     * @param userName 账号
     * @param userPassword 密码
     * @return
     */
    public WebUser getWebUserByUserNameAndUserPassword(@Param("userName") String userName,@Param("userPassword") String userPassword);

    /**
     * 根据id查询管理员信息
     * @param userId 管理员id
     * @return
     */
    public WebUser getWebUserByUserId(Integer userId);

    /**
     * 新增管理员信息
     * @param webUser
     * @return返回Integer值
     */
    public Integer addWebUser(WebUser webUser);

    /**
     * 删除管理员信息
     * @param userId 索要删除的管理员id
     * @return返回Integer值
     */
    public Integer delWebUser(Integer userId);

    /**
     * 修改管理员信息
     * @param webUser
     * @return返回Integer值
     */
    public Integer editWebUser(WebUser webUser);
}
