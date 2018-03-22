package com.neweagle.api.module.sys.auth;

import com.alibaba.fastjson.JSON;
import com.neweagle.api.comm.plugin.security.model.AuthUser;
import com.neweagle.api.module.sys.entity.AppUser;

/**
 * The type Auth user factory.
 *
 * @author tjp
 */
public final class AuthUserFactory {

    private AuthUserFactory() {
    }

    /**
     * Create auth user.
     *
     * @param user the user
     * @return the auth user
     */
    public static AuthUser create(AppUser user) {
        AuthUser authUser = JSON.parseObject(JSON.toJSONString(user),AuthUser.class);
        return authUser;
    }

}
