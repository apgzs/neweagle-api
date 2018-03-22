package com.neweagle.api.comm.plugin.security;

import com.alibaba.fastjson.JSON;
import com.neweagle.api.comm.plugin.security.model.AuthUser;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * The type Token util.
 *
 * @author zhangxd
 */
@Component
@ConfigurationProperties("security.jwt")
public class TokenUtil extends AbstractTokenUtil {

    @Override
    public UserDetails getUserDetails(String token) {
        String userDetailsString = getUserDetailsString(token);
        if (userDetailsString != null) {
            return JSON.parseObject(userDetailsString, AuthUser.class);
        }
        return null;
    }

}