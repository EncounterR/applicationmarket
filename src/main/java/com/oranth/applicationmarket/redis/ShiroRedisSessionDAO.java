package com.oranth.applicationmarket.redis;
import java.io.Serializable;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;

/**
 * ShiroRedisCacheSessionDAO
 */
public class ShiroRedisSessionDAO extends CachingSessionDAO {
	

	
    @Override
    protected void doUpdate(Session session) {
        this.getCacheManager().getCache(this.getClass().getName()).put(session.getId(), session);
    }

    @Override
    protected void doDelete(Session session) {
        this.getCacheManager().getCache(this.getClass().getName()).remove(session.getId());
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        this.getCacheManager().getCache(this.getClass().getName()).put(session.getId(), session);
        return sessionId;

    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        return (Session) this.getCacheManager().getCache(this.getClass().getName()).get(sessionId);
    }
}