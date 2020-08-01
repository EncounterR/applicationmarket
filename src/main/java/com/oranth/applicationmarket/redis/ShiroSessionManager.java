package com.oranth.applicationmarket.redis;

import java.io.Serializable;

import javax.servlet.ServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;

public class ShiroSessionManager extends DefaultWebSessionManager{
	@Override
	protected org.apache.shiro.session.Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
		Serializable sessionId = getSessionId(sessionKey);
 
		ServletRequest request = null;
		if (sessionKey instanceof WebSessionKey) {
			request = ((WebSessionKey) sessionKey).getServletRequest();
		}
 
		if (request != null && null != sessionId) {
			Object sessionObj = request.getAttribute(sessionId.toString());
			if (sessionObj != null) {
				return (org.apache.shiro.session.Session) sessionObj;
			}
		}
		Session session = null;
		try {
			session = super.retrieveSession(sessionKey);
		} catch (Exception e) {
			
		}
		
		if (request != null && null != sessionId) {
			request.setAttribute(sessionId.toString(), session);
		}
		return session;
	}
}
