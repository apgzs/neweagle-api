package com.neweagle.api.module.sys.auth;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.neweagle.api.comm.enums.EnableEnum;
import com.neweagle.api.module.sys.entity.AppUser;
import com.neweagle.api.module.sys.service.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Security AppUser Detail Service
 *
 * @author tjp
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * 用户服务
     */
    @Autowired
    private IAppUserService appUserService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        AppUser user = appUserService.selectOne(new EntityWrapper<AppUser>().eq("username",username).eq("enabled", EnableEnum.ENABLE));
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return AuthUserFactory.create(user);
        }
    }
}
