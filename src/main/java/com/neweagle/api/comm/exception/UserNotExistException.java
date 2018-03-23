package com.neweagle.api.comm.exception;


import com.neweagle.api.comm.exception.base.BusinessException;

/**
 * 用户不存在
 *
 * @author tjp
 */
public class UserNotExistException extends BusinessException {

    public UserNotExistException(String message) {
        super(message);
    }
    public UserNotExistException() {
        super("用户不存在!");
    }

}
