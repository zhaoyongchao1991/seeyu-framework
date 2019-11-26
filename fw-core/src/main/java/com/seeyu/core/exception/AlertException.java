package com.seeyu.core.exception;


/**
 * 异常,携带警告消息
 * @author
 *
 */
public class AlertException extends ServiceException{


	public AlertException(String message, Object ...arguments){
		super(message, arguments);
	}


	public AlertException(String message, Throwable cause, Object ...arguments) {
		super(message, cause, arguments);
	}



}
