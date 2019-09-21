package com.seeyu.core.exception;

/**
 * 用户权限验证失败(访问数据时权限不足)时抛出此异常
 * @author seeyu
 */
public class AuthorityAuthenticationFailException extends Exception{

	public AuthorityAuthenticationFailException(){
		super();
	}
	public AuthorityAuthenticationFailException(String msg){
		super(msg);
	}
}
