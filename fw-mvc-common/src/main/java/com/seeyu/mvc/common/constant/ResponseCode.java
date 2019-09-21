package com.seeyu.mvc.common.constant;

/**
 * @author seeyu
 */

public class ResponseCode {

    /**
     * 请求响应正常
     */
    public static int SUCCESS_CODE = 200;

    /**
     * 服务器发生(预料之中)的异常,但是已经被处理
     */
    public static int NORMAL_EXCEPTION = 300;

    /*
     * 服务器发生预料之外的异常,未被处理
     */
    public static int SERVER_EXCEPTION = 509;

    /**
     * 其它异常,且未被处理 例如:框架异常
     */
    public static int OTHER_EXCEPTION = 589;

    /*
     * 用户未登录,或会话丢失
     */
    public static int LOGIN_AUTHENTICATION_FAIL = 809;

    /*
     * 用户权限验证失败(访问数据时权限不足)
     */
    public static int AUTHORITY_AUTHENTICATION_FAIL = 819;


}
