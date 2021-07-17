package com.blog.common.controlleradvice;

import com.blog.common.cache.RedisManager;
import com.blog.common.result.Result;
import com.blog.oauth.OAuthSessionManager;
import org.apache.shiro.session.Session;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@ControllerAdvice
@Deprecated
public class ClearTokenResponseBodyAdvice implements ResponseBodyAdvice {

    //@Autowired
    private RedisManager redisManager;


    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return returnType.getGenericParameterType().equals(Result.class);
    }


    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        HttpServletRequest httpRequest = ((ServletServerHttpRequest) request).getServletRequest();
        String token = httpRequest.getHeader(OAuthSessionManager.OAUTH_TOKEN);

        HttpServletResponse httpResponse = ((ServletServerHttpResponse) response).getServletResponse();

        if (null != token) {
            Session s = redisManager.get(token, Session.class);

            if (null == s || null == s.getId()) {
                httpResponse.setHeader("SESSION_TIME_OUT", "timeout");
            }
        }


        return body;
    }


    public RedisManager getRedisManager() {
        return redisManager;
    }

    public void setRedisManager(RedisManager redisManager) {
        this.redisManager = redisManager;
    }


}
