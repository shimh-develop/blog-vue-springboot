package com.shimh.oauth;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.alibaba.fastjson.JSON;
import com.shimh.common.cache.RedisManager;

public class OAuthSessionDAO extends CachingSessionDAO implements InitializingBean{
	
	private static Logger logger = LoggerFactory.getLogger(OAuthSessionDAO.class); 
	
	private RedisManager redisManager;
	
	private Map<String,Session> map = new HashMap<String,Session>();
	
	
	@Override
	protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        logger.info(sessionId.toString());
        
        //redisManager.set(sessionId.toString(), JSON.toJSONString(session) , RedisManager.DEFAULT_EXPIRE);
        map.put(sessionId.toString(), session);
        return sessionId;
	}
	

	@Override
	protected void doUpdate(Session session) {
		if(session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {
            return; //如果会话过期/停止 没必要再更新了
        }
		 logger.info(session.getId().toString());
        //redisManager.set(session.getId().toString(), JSON.toJSONString(session), RedisManager.DEFAULT_EXPIRE);
		 map.put(session.getId().toString(), session);
	}

	@Override
	protected void doDelete(Session session) {
		//redisManager.delete(session.getId().toString());
		map.remove(session.getId().toString());
	}

	

	@Override
	protected Session doReadSession(Serializable sessionId) {
		logger.info(sessionId.toString());
		//return JSON.parseObject(redisManager.get(sessionId.toString()).toString(), Session.class)   ;
		return map.get(sessionId.toString());
	}


	
	public RedisManager getRedisManager() {
		return redisManager;
	}

	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if(null == this.redisManager){
			 logger.error("StringRedisTemplate should be not null."); 
		}
		
	}

	

}
