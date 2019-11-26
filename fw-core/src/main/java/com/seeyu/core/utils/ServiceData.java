package com.seeyu.core.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seeyu.core.exception.AlertException;
import com.seeyu.core.exception.AlertRuntimeException;
import com.seeyu.core.exception.ServiceException;

/**
 * @author seeyu
 */
public class ServiceData<T> {

    protected boolean success = false;
    protected String message = null;
    @JsonIgnore
    protected Object[] messageArgs;
    protected T data;

    public ServiceData() {
        this(false);
    }

    public ServiceData(boolean success) {
        this.success = success;
    }


    public static ServiceData SUCCESS() {
        return new ServiceData(true);
    }

    public static ServiceData ERROR() {
        return new ServiceData(false);
    }

    public boolean isSuccess() {
        return success;
    }

    public ServiceData setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ServiceData setMessage(String message, Object...messageArgs) {
        this.message = message;
        this.messageArgs = messageArgs;
        return this;
    }

    public T getData() {
        return data;
    }

    public ServiceData setData(T data) {
        this.data = data;
        return this;
    }

    public Object[] getMessageArgs() {
        return messageArgs;
    }

    /**
     * 结束方法 向上抛出异常
     */
    public ServiceData<T> raise() throws ServiceException {
        if(!this.success){
            throw new AlertException(this.message, this.messageArgs);
        }
        return this;
    }
}
