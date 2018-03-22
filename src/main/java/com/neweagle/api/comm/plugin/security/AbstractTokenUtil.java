package com.neweagle.api.comm.plugin.security;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.neweagle.api.comm.plugin.redis.RedisRepository;
import com.neweagle.api.comm.utils.StringHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

/**
 * Token 工具类
 *
 * @author tjp
 */
public abstract class AbstractTokenUtil {

    /**
     * Logger
     */
    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractTokenUtil.class);

    /**
     * Token 类型
     */
    public static final String TOKEN_TYPE_BEARER = "Bearer";
    /**
     * 权限缓存前缀
     */
    private static final String REDIS_PREFIX_AUTH = "auth:";
    /**
     * 用户信息缓存前缀
     */
    private static final String REDIS_PREFIX_USER = "user-details:";

    /**
     * redis repository
     */
    @Autowired
    private RedisRepository redisRepository;
    /**
     * secret
     */
    private String secret;
    /**
     * 过期时间
     */
    private Long expiration;

    /**
     * 获取用户名
     *
     * @param token Token
     * @return String
     */
    public String getUsernameFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims != null ? claims.getSubject() : null;
    }

    /**
     * 获取过期时间
     *
     * @param token Token
     * @return Date
     */
    public Date getExpiredFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims != null ? claims.getExpiration() : null;
    }

    /**
     * 获得 Claims
     *
     * @param token Token
     * @return Claims
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        } catch (Exception e) {
            LOGGER.warn("getClaimsFromToken exception", e);
            claims = null;
        }
        return claims;
    }

    /**
     * 计算过期时间
     *
     * @return Date
     */
    private Date generateExpired() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 判断 Token 是否过期
     *
     * @param token Token
     * @return Boolean
     */
    private Boolean isTokenExpired(String token) {
        Date expirationDate = getExpiredFromToken(token);
        return expirationDate.before(new Date());
    }

    /**
     * 生成 Token
     *
     * @param userDetails 用户信息
     * @return String
     */
    public String generateToken(UserDetails userDetails) {
        String token = Jwts.builder()
            .setSubject(userDetails.getUsername())
            .setExpiration(generateExpired())
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();

        String key = REDIS_PREFIX_AUTH + userDetails.getUsername();
        redisRepository.setExpire(key, token, expiration);
        putUserDetails(userDetails);
        return token;
    }

    /**
     * 描述：根据用户名创建token
     * @param userName
     * @return
     */
    public String generateTokenByUserName(String userName,Long userId) {
        String token = Jwts.builder()
                .setSubject(userName)
                .setExpiration(generateExpired())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

        String key = REDIS_PREFIX_AUTH + userName;
        redisRepository.setExpire(key, token, expiration);
        putUserDetailsByUserName(userName,userId);
        return token;
    }

    /**
     * 验证 Token
     *
     * @param token Token
     * @return Boolean
     */
    public Boolean validateToken(String token) {
        final String username = getUsernameFromToken(token);
        String key = REDIS_PREFIX_AUTH + username;
        String redisToken = redisRepository.get(key);
        return StringHelper.isNotEmpty(token) && !isTokenExpired(token) && token.equals(redisToken);
    }

    /**
     * 移除 Token
     *
     * @param token Token
     */
    public void removeToken(String token) {
        final String username = getUsernameFromToken(token);
        String key = REDIS_PREFIX_AUTH + username;
        redisRepository.del(key);
        delUserDetails(username);
    }

    /**
     * 获得用户信息 Json 字符串
     *
     * @param token Token
     * @return String
     */
    protected String getUserDetailsString(String token) {
        final String username = getUsernameFromToken(token);
        String key = REDIS_PREFIX_USER + username;
        return redisRepository.get(key);
    }

    /**
     * 获得用户信息
     *
     * @param token Token
     * @return UserDetails
     */
    public abstract UserDetails getUserDetails(String token);

    /**
     * 存储用户信息
     *
     * @param userName 用户信息
     */
    private void putUserDetailsByUserName(String userName,Long userId) {
        String key = REDIS_PREFIX_USER + userName;
        //构建用户详情
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonArrayObject = new JSONObject();
        jsonArrayObject.put("authority","ROLE_USER");
        jsonArray.add(jsonArrayObject);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("accountNonExpired",true);
        jsonObject.put("accountNonLocked",true);
        jsonObject.put("credentialsNonExpired",true);
        jsonObject.put("authorities",jsonArray);
        jsonObject.put("id",userId);
        jsonObject.put("username",userName);
        jsonObject.put("mobile",userName);
        redisRepository.setExpire(key, JSON.toJSONString(jsonObject), expiration);
    }
    /**
     * 存储用户信息
     *
     * @param userDetails 用户信息
     */
    private void putUserDetails(UserDetails userDetails) {
        String key = REDIS_PREFIX_USER + userDetails.getUsername();
        redisRepository.setExpire(key, JSON.toJSONString(userDetails), expiration);
    }

    /**
     * 删除用户信息
     *
     * @param username 用户名
     */
    private void delUserDetails(String username) {
        String key = REDIS_PREFIX_USER + username;
        redisRepository.del(key);
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }
}