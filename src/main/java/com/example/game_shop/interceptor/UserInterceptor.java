package com.example.game_shop.interceptor;

import com.example.game_shop.exception.TokenAuthExpiredException;
import com.example.game_shop.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author sheng
 * @date 2021/11/30 12:26
 */
@Component
public class UserInterceptor implements HandlerInterceptor {
    @Value("${token.yangToken}")
    private Long yangToken;

    @Value("${token.oldToken}")
    private Long oldToken;

    @Resource
    private TokenUtil tokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            // OPTIONS请求得放行
            return true;
        }
        String token = request.getHeader("token");
        if (null == token || "".equals(token.trim())) {
            System.out.println("token为空");
            System.out.println("method:" + request.getRequestURI());
            return false;
        }

        Map<String, String> origin = tokenUtil.parseToken(token);
        long timeOfUse = System.currentTimeMillis() - Long.parseLong(origin.get("timeStamp"));

        // token有效就往下执行，无效就不执行
        if (tokenUtil.checkToken(token)){
            // 判断 token 是否过期
            if (timeOfUse >= yangToken && timeOfUse < oldToken) {
                // 老年 token 就刷新 token
                tokenUtil.setToken(origin.get("account"), origin.get("id"), response);
            } else if (timeOfUse >= oldToken) {
                //过期 token 就返回 token 无效.
                throw new TokenAuthExpiredException();
            }
            return true;
        } else {
            return false;
        }
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
