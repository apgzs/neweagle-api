package com.neweagle.api.comm.exception;


import com.neweagle.api.comm.exception.base.BusinessException;

/**
 * 手机号码不合法
 *
 * @author tjp
 */
public class IllegalMobileException extends BusinessException {

    public IllegalMobileException(String message) {
        super(message);
    }

}
