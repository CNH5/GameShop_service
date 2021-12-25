package com.example.game_shop.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.game_shop.exception.TokenAuthExpiredException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sheng
 * @date 2021/11/30 9:33
 */
@Component
public class TokenUtil {
    @Value("${token.yangToken}")
    private Long yangToken;

    @Value("${token.oldToken}")
    private Long oldToken;

    @Value("${token.privateKey}")
    private String privateKey;


    /**
     * 将用户账号和id加密为token
     *
     * @return token
     */
    public String parseToken(String account, String id) {
        return JWT
                .create()
                .withClaim("account", account)
                .withClaim("id", id)
                .withClaim("timeStamp", System.currentTimeMillis())
                .sign(Algorithm.HMAC256(privateKey));
    }

    /**
     * 给回复头添加token
     */
    public void setToken(String account, String id, HttpServletResponse response) {
        response.addHeader("Access-Control-Expose-Headers", "token");
        response.setHeader("token", parseToken(account, id));
    }

    /**
     * 将token转化为原来的内容
     */
    public Map<String, String> getTokenData(String token) {
        HashMap<String, String> map = new HashMap<>();
        DecodedJWT decodedjwt = JWT.require(Algorithm.HMAC256(privateKey)).build().verify(token);

        map.put("account", decodedjwt.getClaim("account").asString());
        map.put("id", decodedjwt.getClaim("id").asString());
        map.put("timeStamp", decodedjwt.getClaim("timeStamp").asLong().toString());
        return map;
    }

    /**
     * 根据使用时间，判断是否需要刷新token
     *
     * @param tokenMap token
     * @param response 回复头
     */
    public void updateToken(Map<String, String> tokenMap, HttpServletResponse response) throws TokenAuthExpiredException {
        long timeOfUse = System.currentTimeMillis() - Long.parseLong(tokenMap.get("timeStamp"));
        if (timeOfUse >= yangToken && timeOfUse < oldToken) {
            // 老年token,刷新token
            setToken(tokenMap.get("account"), tokenMap.get("id"), response);
        } else if (timeOfUse >= oldToken) {
            // 过期token,返回token无效
            throw new TokenAuthExpiredException();
        }
    }
}
