package com.neweagle.api.comm.plugin.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Security AppUser
 *
 * @author tjp
 */
@Data
public class AuthUser extends AbstractAuthUser {

    /**
     * 用户默认角色
     */
    private static final String TRIP_USER_ROLE = "ROLE_USER";
    /**
     * id
     */
    private Long id;
    /**
     * 用户登录名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 冻结
     */
    private boolean enabled;


    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(TRIP_USER_ROLE));
        return authorities;
    }


}
