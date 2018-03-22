package com.neweagle.api.module.sys.controller;

import com.alibaba.fastjson.JSON;
import com.neweagle.api.comm.plugin.security.TokenUtil;
import com.neweagle.api.comm.utils.DateHelper;
import com.neweagle.api.comm.utils.StringHelper;
import com.neweagle.api.comm.web.base.SuperController;
import com.neweagle.api.comm.web.json.JsonResult;
import com.neweagle.api.module.sys.service.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户类控制器
 * <p>
 */
@RestController
@RequestMapping("/sys/appuser")
public class AppUserController extends SuperController {

    @Autowired
    private IAppUserService appUserService;
    /**
     * 权限管理
     */
    @Autowired
    private AuthenticationManager authenticationManager;
    /**
     * 用户信息服务
     */
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserDetailsService userDetailsService;
    /**
     * Token工具类
     */
    @Autowired
    private TokenUtil jwtTokenUtil;

    /**
     * Create authentication token map.
     * @param username the username
     * @param password the password
     * @return the map
     */
    @PostMapping(value = "/v1/token")
    public Object createAuthenticationToken(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {

        //完成授权
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        final String token = jwtTokenUtil.generateToken(userDetails); //生成Token

        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("access_token", token);
        tokenMap.put("expires_in", jwtTokenUtil.getExpiration());
        tokenMap.put("token_type", TokenUtil.TOKEN_TYPE_BEARER);

        return JsonResult.success(tokenMap);
    }


    /**
     * 刷新token
     *
     * @param token
     * @return Object
     */
    @GetMapping(value = "/refresh", produces = "application/json; charset=UTF-8")
    public Object refreshAndGetAuthenticationToken(String token) {
        //重新生成Token
        String username = jwtTokenUtil.getUsernameFromToken(token);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String refreshedToken = jwtTokenUtil.generateToken(userDetails);

        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("access_token", refreshedToken);
        tokenMap.put("expires_in", jwtTokenUtil.getExpiration());
        tokenMap.put("token_type", TokenUtil.TOKEN_TYPE_BEARER);

        return JsonResult.success(tokenMap);
    }


    /**
     * 描述：注册
     * @param mobile
     * @param verifyCode
     * @param password
     * @return
     */
    @ResponseBody
    @PostMapping("/v1/register")
    public Object register(String mobile,String verifyCode,String password,Integer userType,Integer source,String position){
        Map map = new HashMap();

        return JsonResult.success(map);
    }


}
