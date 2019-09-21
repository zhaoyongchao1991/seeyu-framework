package com.seeyu.core.exception;


/**
 * 运行时异常,携带警告消息
 * @author
 *
 */
public class AlertRuntimeException extends RuntimeException{

	private Object[] arguments;

	public AlertRuntimeException(String message, Object ...arguments){
		super(message);
		this.arguments = arguments;
	}


	public AlertRuntimeException(String message, Throwable cause, Object ...arguments) {
		super(message, cause);
		this.arguments = arguments;
	}


	public Object[] getArguments() {
		return arguments;
	}




}
