package com.neweagle.api.comm.plugin.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;

/**
 * spring-security配置
 *
 * @author tjp
 */
@Configuration
@Order(1)
public class WebSecurityConfig extends AbstractWebSecurityConfig {


    @Override
    public void configure(WebSecurity web) throws Exception {
        //忽略权限校验的访问路径
        web.ignoring()
                .antMatchers(
                        "/sys/appuser/*/token",
                        "/sys/appuser/*/register",
                        "/sys/appuser/*/refresh-token",
                        "/druid/**"

                );
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,
                        "/sys/appuser/*/token"
                        ).permitAll();
        super.configure(security);
    }
}
