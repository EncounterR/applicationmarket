package com.oranth.applicationmarket.entity;


import java.io.Serializable;

import com.oranth.applicationmarket.entity.WebRole;
import org.springframework.stereotype.Component;

/**
 * 用户身份信息，存入session 由于tomcat将session会序列化在本地硬盘上，所以使用Serializable接口
 * 
 * 
 */
@Component
public class ActiveUser implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -4086690360180730665L;
	private int userid;// 用户id
	private String username;// 用户名称
	private Integer userRoleId;//用户所属角色id
	private WebRole webRole;//用户所属角色信息

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	public WebRole getWebRole() {
		return webRole;
	}

	public void setWebRole(WebRole webRole) {
		this.webRole = webRole;
	}

	public ActiveUser(int userid, String username, Integer userRoleId, WebRole webRole) {
		this.userid = userid;
		this.username = username;
		this.userRoleId = userRoleId;
		this.webRole = webRole;
	}

	public ActiveUser() {
	}

	@Override
	public String toString() {
		return "ActiveUser{" +
				"userid=" + userid +
				", username='" + username + '\'' +
				", userRoleId=" + userRoleId +
				", webRole=" + webRole +
				'}';
	}
}

