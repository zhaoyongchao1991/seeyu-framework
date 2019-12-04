package com.seeyu.core.exception;

/**
 * 登录验证错误(用户未登录或者session失效)时抛出此异常
 * @author seeyu
 */
public class LoginAuthenticationFailRuntimeException extends RuntimeException{

	public LoginAuthenticationFailRuntimeException(){
		super();
	}

	public LoginAuthenticationFailRuntimeException(String msg){
		super(msg);
	}
}
