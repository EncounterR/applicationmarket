
package com.oranth.applicationmarket.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {

	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		System.out.println("执行ShiroConfig");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean(); // 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// setLoginUrl
		// 如果不设置值，默认会自动寻找Web工程根目录下的"/index.html"页面 或 "/login" 映射
		shiroFilterFactoryBean.setLoginUrl("/index.html");
		// 设置无权限时跳转的 url;
		shiroFilterFactoryBean.setUnauthorizedUrl("/404.html");
		// 设置拦截器
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		// 开放登陆接口
		filterChainDefinitionMap.put("/web-user/login", "anon");
		// 开放静态资源
		filterChainDefinitionMap.put("/common/**", "anon");
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("fonts/**", "anon");
		filterChainDefinitionMap.put("/img/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/live2d/**", "anon");
		filterChainDefinitionMap.put("/echarts/**", "anon");
		// 其余接口一律拦截 //
		// * 主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截 
		filterChainDefinitionMap.put("/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		System.out.println("Shiro拦截器工厂类注入成功");
		return shiroFilterFactoryBean;
	}
	/**
	 * 自定义身份认证 realm;
	 * <p>
	 * 必须写这个类，并加上 @Bean 注解，目的是注入 CustomRealm， 否则会影响 CustomRealm类 中其他类的依赖注入
	 */
	@Bean
	public CustomRealm customRealm() {
		CustomRealm customRealm=new CustomRealm();
		return customRealm;
	}
	/**
	 * 
	 * 注入 securityManager
	 */
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(); // 设置realm.
		securityManager.setRealm(customRealm());
		return securityManager;
	}

	
}