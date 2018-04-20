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

/**
 * 将session保存到redis
 *
 * @author shimh
 * <p>
 * 2018年1月23日
 */
public class OAuthSessionDAO extends CachingSessionDAO implements InitializingBean {

    private static Logger logger = LoggerFactory.getLogger(OAuthSessionDAO.class);

    private RedisManager redisManager;


    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        logger.info(sessionId.toString());

        redisManager.set(sessionId.toString(), session, RedisManager.DEFAULT_EXPIRE);
        return sessionId;
    }


    @Override
    protected void doUpdate(Session session) {
        if (session instanceof ValidatingSession && !((ValidatingSession) session).isValid()) {
            return; //如果会话过期/停止 没必要再更新了
        }
        logger.info(session.getId().toString());
        redisManager.set(session.getId().toString(), session, RedisManager.DEFAULT_EXPIRE);
    }

    @Override
    protected void doDelete(Session session) {
        redisManager.delete(session.getId().toString());
    }


    @Override
    protected Session doReadSession(Serializable sessionId) {
        logger.info(sessionId.toString());
        return redisManager.get(sessionId.toString(), Session.class);
    }


    public RedisManager getRedisManager() {
        return redisManager;
    }

    public void setRedisManager(RedisManager redisManager) {
        this.redisManager = redisManager;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (null == this.redisManager) {
            logger.error("StringRedisTemplate should be not null.");
        }

    }


}
