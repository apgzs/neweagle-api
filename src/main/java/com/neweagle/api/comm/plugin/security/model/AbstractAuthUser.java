package com.neweagle.api.comm.plugin.security.model;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * 描述：Security NSysAppUser
 */
public abstract class AbstractAuthUser implements UserDetails {

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
