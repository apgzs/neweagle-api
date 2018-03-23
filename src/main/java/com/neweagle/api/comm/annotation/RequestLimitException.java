package com.neweagle.api.comm.annotation;


import com.neweagle.api.comm.exception.base.BusinessException;

/**
 * The type Request limit exception.
 *
 * @author tjp
 */
public class RequestLimitException extends BusinessException {

    private static final long serialVersionUID = 1364225358754654702L;

    /**
     * Instantiates a new Request limit exception.
     */
    public RequestLimitException() {
        super("短信请求太过频繁");
    }

    /**
     * Instantiates a new Request limit exception.
     *
     * @param message the message
     */
    public RequestLimitException(String message) {
        super(message);
    }

}