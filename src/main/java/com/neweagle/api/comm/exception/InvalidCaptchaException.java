package com.neweagle.api.comm.exception;


import com.neweagle.api.comm.exception.base.BusinessException;

/**
 * 无效验证码
 *
 * @author tjp
 */
public class InvalidCaptchaException extends BusinessException {

    public InvalidCaptchaException(String message) {
        super(message);
    }
    public InvalidCaptchaException() {
        super("无效验证码!");
    }

}
