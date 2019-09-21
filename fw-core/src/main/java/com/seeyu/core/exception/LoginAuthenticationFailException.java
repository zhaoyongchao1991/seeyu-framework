package com.seeyu.core.exception;

/**
 * 登录验证错误(用户未登录或者session失效)时抛出此异常
 * @author seeyu
 */
public class LoginAuthenticationFailException extends Exception{
	
	public LoginAuthenticationFailException(){
		super();
	}
	public LoginAuthenticationFailException(String msg){
		super(msg);
	}
}
