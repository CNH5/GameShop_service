package com.example.game_shop.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
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
    public String getToken(String account, String id) {
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
        response.setHeader("token", getToken(account, id));
    }

    /**
     * 将token转化为原来的内容
     */
    public Map<String, String> parseToken(String token) {
        HashMap<String, String> map = new HashMap<>();
        DecodedJWT decodedjwt = JWT.require(Algorithm.HMAC256(privateKey)).build().verify(token);
        Claim account = decodedjwt.getClaim("account");
        Claim id = decodedjwt.getClaim("id");
        Claim timeStamp = decodedjwt.getClaim("timeStamp");

        map.put("account", account.asString());
        map.put("id", id.asString());
        map.put("timeStamp", timeStamp.asLong().toString());
        return map;
    }

    /**
     * 根据使用时间，判断是否需要刷新token
     *
     * @param account   账号
     * @param id        用户id
     * @param timeOfUse 使用时间
     * @param response  回复头
     */
    public void updateToken(String account, String id, long timeOfUse, HttpServletResponse response) throws TokenAuthExpiredException {
        if (timeOfUse >= yangToken && timeOfUse < oldToken) {
            // 老年token,刷新token
            setToken(account, id, response);
        } else if (timeOfUse >= oldToken) {
            // 过期token,返回token无效.
            throw new TokenAuthExpiredException();
        }
    }
}