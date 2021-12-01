package com.example.game_shop.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.game_shop.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sheng
 * @date 2021/11/30 9:33
 */
@Component
public class TokenUtil {
    @Resource
    private UserMapper userMapper;

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
     * 检验token是否有效
     *
     * @return 有效返回true，否则返回false
     */
    public boolean checkToken(String token) {
        Map<String, String> origin = parseToken(token);
        return origin.get("id") != null &&
                origin.get("account") != null &&
                origin.get("id").equals(userMapper.getId(origin.get("account")));
    }

    /**
     * 检验账号和请求头中携带的账号是否一致
     *
     * @return 一致返回true，否则返回false
     */
    public boolean check(String account, HttpServletRequest request) {
        return getAccount(request).equals(account);
    }

    /**
     * 从请求头中获取token的账号项
     */
    public String getAccount(HttpServletRequest request) {
        return parseToken(request.getHeader("token")).get("account");
    }

    /**
     * 从请求头中获取token的id项
     */
    public String getIdent(HttpServletRequest request) {
        return userMapper.getIdent(getAccount(request));
    }
}
