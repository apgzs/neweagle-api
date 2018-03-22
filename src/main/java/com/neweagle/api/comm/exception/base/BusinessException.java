package com.neweagle.api.comm.exception.base;

/**
 * 业务异常.
 *
 * @author tjp
 */
public class BusinessException extends RuntimeException {

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

}