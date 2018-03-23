package com.neweagle.api.comm.exception;


import com.neweagle.api.comm.exception.base.BusinessException;

/**
 * 用户已存在
 *
 * @author tjp
 */
public class UserExistException extends BusinessException {

    public UserExistException(String message) {
        super(message);
    }
    public UserExistException() {
        super("用户已存在!");
    }

}
