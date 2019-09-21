package com.seeyu.core.exception;

/**
 * 断言异常
 * @author seeyu
 */
public class AssertionRuntimeException extends RuntimeException{
    public AssertionRuntimeException(){
        super();
    }

    public AssertionRuntimeException(String message) {
        super(message);
    }

    public AssertionRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public AssertionRuntimeException(String message, Object ...arguments){
        super(message);
    }

    @Deprecated
    public AssertionRuntimeException(String message, Throwable cause, Object ...arguments) {
        super(message, cause);
    }

}
