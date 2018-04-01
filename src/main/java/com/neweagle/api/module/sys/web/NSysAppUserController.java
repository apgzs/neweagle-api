package com.neweagle.api.module.sys.web;

import com.neweagle.api.comm.plugin.security.TokenUtil;
import com.neweagle.api.comm.web.base.SuperController;
import com.neweagle.api.comm.web.json.JsonResult;
import com.neweagle.api.module.sys.service.INSysAppUserService;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户类控制器
 * <p>
 */
@Validated
@RestController
@RequestMapping("/api/sys/appuser")
public class NSysAppUserController extends SuperController {

    @Autowired
    private INSysAppUserService appUserService;
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
     * 获取token
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
    @GetMapping(value = "/v1/refresh-token")
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
    @PostMapping("/v1/register")
    public Object register(
            @Pattern(regexp = "1(3[0-9]|4[57]|5[0-35-9]|7[01678]|8[0-9])\\d{8}", message = "手机号码格式错误")@RequestParam("mobile")String mobile,
            @Pattern(regexp = "\\d{4}", message = "验证码为6位数字") String verifyCode,
            @Length(min = 6, max = 20, message = "密码长度为6到20")String password) throws Exception{
        if (appUserService.register(mobile,verifyCode,password)){
            return JsonResult.success();
        }else {
            return JsonResult.error("注册失败，请稍后再试！");
        }
    }


}
