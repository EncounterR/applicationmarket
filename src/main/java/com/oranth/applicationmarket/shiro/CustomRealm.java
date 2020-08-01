package com.oranth.applicationmarket.shiro;


import com.oranth.applicationmarket.entity.WebRole;
import com.oranth.applicationmarket.entity.WebUser;
import com.oranth.applicationmarket.entity.ActiveUser;
import com.oranth.applicationmarket.mapper.WebUserMapper;
import com.oranth.applicationmarket.service.IWebRoleService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomRealm extends AuthorizingRealm {

	@Autowired
	private WebUserMapper webUserMapper;
	@Autowired
	private IWebRoleService iWebRoleService;

	/**
	 *
	 * 获取身份验证信息 Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。认证
	 * @param token 用户身份信息 token
	 * @return 返回封装了用户信息的 AuthenticationInfo 实例
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		System.out.println("执行认证操作");
		//token中获取用户名密码
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        //从数据库 根据用户名查询 
        WebUser webUser = webUserMapper.getWebUserByUserName(username);
        if(webUser==null){
            throw new UnknownAccountException("用户名或密码错误！");
        }
        if(!password.equals(webUser.getUserPassword())){
            throw new IncorrectCredentialsException("用户名或密码错误！");
        }
        WebRole webRole= iWebRoleService.getWebRoleByRoleId(webUser.getRoleId());
		ActiveUser activeUser = new ActiveUser();
		activeUser.setUserid(webUser.getUserId());
		activeUser.setUsername(webUser.getUserName());
		activeUser.setUserRoleId(webUser.getRoleId());
		activeUser.setWebRole(webRole);
        System.out.println("认证结束");
		//创建 SimpleAuthenticationInfo会将用户信息放入shiro维护的用户集合
        return new SimpleAuthenticationInfo(activeUser, password, getName());
	}

	/**
	 * 获取授权信息
	 * @param principals
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		/*System.out.println("执行授权操作");
		// 获取的认证的身份标识 new SimpleAuthenticationInfo(activeUser, password,getName())
		ActiveUser activeUser = (ActiveUser) principals.getPrimaryPrincipal();
		//用户所属角色
		Integer userRoleId=activeUser.getUserRoleId();
		// 根据用户所属角色id查询到角色信息
		WebRole webRole=sysRoleService.getSysRoleAndSysModleById(userRoleId);
		// 将权限信息封闭为AuthorizationInfo
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		for(SysModle parentSysModle:sysRole.getMlist()) {
			for(SysModle sonSysModle:parentSysModle.getSysModles()) {
				simpleAuthorizationInfo.addStringPermission(sonSysModle.getRight_url());
			}
		}*/
		return null;
	}

}
