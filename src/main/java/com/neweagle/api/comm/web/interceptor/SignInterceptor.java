package com.neweagle.api.comm.web.interceptor;

import com.neweagle.api.comm.utils.SignUtil;
import com.neweagle.api.comm.utils.StringHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 拦截器 请求参数签名校验
 * Created by tjp
 * 所有请求的接口必须携带sign、timestamp参数
 * 签名方法是进行参数签名 具体见SignUtil类
 */
@Slf4j
public class SignInterceptor implements HandlerInterceptor {

    public static final String VERIFY_FAIL_MSG = "The request parameter signature verification failed!";

    public static final long SIGN_EXPIRE = 30;//签名有效期  秒

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        // 签名
        String signStr = request.getParameter("sign");
        String timestamp = request.getParameter("timestamp");
        if (StringHelper.isBlank(signStr)||StringHelper.isBlank(timestamp)) {
            log.warn(VERIFY_FAIL_MSG);
            response.setStatus(HttpStatus.SC_FORBIDDEN);
            return false;
        }

        Enumeration<?> pNames =  request.getParameterNames();
        Map<String, Object> params = new HashMap();
        while (pNames.hasMoreElements()) {
            String pName = (String) pNames.nextElement();
            if("sign".equals(pName)||"[]".equals(pName))continue;
            Object pValue = request.getParameter(pName);
            params.put(pName, pValue);
        }

        if(SignUtil.verifySign(params,signStr,SIGN_EXPIRE)){
            return true;
        }

        log.warn(VERIFY_FAIL_MSG);
        response.setStatus(HttpStatus.SC_FORBIDDEN);
        return false;
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {
        return ;
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        return ;
    }

}

