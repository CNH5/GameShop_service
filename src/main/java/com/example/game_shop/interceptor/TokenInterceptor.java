package com.example.game_shop.interceptor;

import com.example.game_shop.annotation.DoWithoutToken;
import com.example.game_shop.exception.AuthInconsistencyException;
import com.example.game_shop.exception.NullTokenException;
import com.example.game_shop.mapper.UserMapper;
import com.example.game_shop.utils.TokenUtil;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 拦截token不正常的请求
 *
 * @author sheng
 * @date 2021/11/30 12:26
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Resource
    private TokenUtil tokenUtil;

    @Resource
    private UserMapper userMapper;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod()) || doWithoutToken(handler)) {
            // OPTIONS和不需要使用token的请求直接放行
            return true;
        }

        String token = request.getHeader("token");
        if (!StringUtils.hasLength(token)) {
            // token为空,拦截
            throw new NullTokenException();
        }

        // 获取token原始的内容
        Map<String, String> origin = tokenUtil.parseToken(token);
        // token有效就往下执行，无效就不执行
        if (origin.get("id") != null && origin.get("id").equals(userMapper.getId(origin.get("account")))) {
            // 判断account和token中的account是否一致
            if (!origin.get("account").equals(request.getParameter("account"))) {
                // 不一致,返回异常
                throw new AuthInconsistencyException();
            }
            // 判断 token 是否需要更新
            tokenUtil.updateToken(origin.get("account"), origin.get("id"),
                    Long.parseLong(origin.get("timeStamp")), response);
            return true;
        }
        return false;
    }

    /**
     * 判断请求是否需要token
     *
     * @return 需要就返回false，不需要返回true
     */
    private boolean doWithoutToken(Object handler) {
        if (handler instanceof HandlerMethod handlerMethod) {
            // 获取方法上的注解
            DoWithoutToken withoutToken = handlerMethod.getMethod().getAnnotation(DoWithoutToken.class);
            // 如果方法上的注解为空 则获取类的注解
            if (withoutToken == null) {
                withoutToken = handlerMethod.getMethod().getDeclaringClass().getAnnotation(DoWithoutToken.class);
            }
            // 如果有注解，就不需要token
            return withoutToken != null;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
