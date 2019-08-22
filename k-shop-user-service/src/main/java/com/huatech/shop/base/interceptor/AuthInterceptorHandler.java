package com.huatech.shop.base.interceptor;

import com.alibaba.fastjson.JSON;
import com.huatech.shop.common.constants.ApiConstants;
import com.huatech.shop.common.exception.ExceptionCustomer;
import com.huatech.shop.module.auth.entity.JwtUser;
import com.huatech.shop.module.auth.entity.JwtUtils;
import com.huatech.shop.module.cache.ICacheService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName AuthIntercptorHandler
 * @Description TODO
 * @Author like
 * @Date 2019-07-25 14:13
 * @Version 1.0
 **/
@Component
@Slf4j
public class AuthInterceptorHandler implements HandlerInterceptor {

    @Autowired
    private JwtUtils jwtUtils;
    @Value("${redis.token.prefix}")
    private String tokenPrefix;
    @Autowired
    private ICacheService cacheService;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {

        String token = httpServletRequest.getHeader("Authorization");
        if (StringUtils.isEmpty(token)) {
            throw new ExceptionCustomer(ApiConstants.TOKEN_FAIL, "missing token");
        }
        //从token里获取用户
        JwtUser jwtUser = jwtUtils.getUserFromToken(token);
        //检查token是否已经过期
        boolean exists = cacheService.exists(tokenPrefix + jwtUser.getUserId());
        if (!exists) {
            throw new ExceptionCustomer(ApiConstants.TOKEN_FAIL, "token had expired");
        } else {
            String cache_token = (String) cacheService.get(tokenPrefix + jwtUser.getUserId());
            if (!cache_token.equals(token)) {
                throw new ExceptionCustomer(ApiConstants.TOKEN_FAIL, "token is not equals");
            }
        }
        //刷新缓存时间
        cacheService.set(tokenPrefix + jwtUser.getUserId(), token, expiration);
        httpServletRequest.setAttribute("userInfo", JSON.toJSONString(jwtUser));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
