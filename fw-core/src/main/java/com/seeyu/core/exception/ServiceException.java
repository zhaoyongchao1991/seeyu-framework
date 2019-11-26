package com.seeyu.core.exception;

import lombok.Getter;

/**
 * @author seeyu
 * @date 2019/11/26
 */
public class ServiceException extends Exception{

    @Getter
    private Object[] arguments;

    public ServiceException(String message, Object ...arguments){
        super(message);
        this.arguments = arguments;
    }


    public ServiceException(String message, Throwable cause, Object ...arguments) {
        super(message, cause);
        this.arguments = arguments;
    }

}
