package com.seeyu.core.utils;

import lombok.Getter;
import lombok.ToString;

@ToString
public class BaseJsonData<T> extends ServiceData<T> {

    protected Exception exception;
    protected String exceptionMessage;
    protected Integer code;
    @Getter
    protected Long timestamp;

    public BaseJsonData() {
        this(false);
    }

    protected BaseJsonData(boolean success) {
        super(success);
        this.timestamp = System.currentTimeMillis();
    }

    public static BaseJsonData SUCCESS() {
        return new BaseJsonData(true);
    }

    public static BaseJsonData ERROR() {
        return new BaseJsonData(false);
    }


    @Override
    public BaseJsonData setSuccess(boolean success) {
        this.success = success;
        return this;
    }


    @Override
    public BaseJsonData setData(T data) {
        this.data = data;
        return this;
    }


    public Exception getException() {
        return exception;
    }


    public BaseJsonData setException(Exception exception) {
        this.exception = exception;
        return this;
    }


    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public BaseJsonData setMessage(String message, Object...messageArgs) {
        this.message = message;
        this.messageArgs = messageArgs;
        return this;
    }




    public String getExceptionMessage() {
        return exceptionMessage;
    }


    public BaseJsonData setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
        return this;
    }


    public Integer getCode() {
        return code;
    }


    public BaseJsonData setCode(Integer code) {
        this.code = code;
        return this;
    }
}
