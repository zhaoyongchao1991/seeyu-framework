package com.seeyu.mvc.constant;

/**
 * @author seeyu
 * @date 2019/7/31
 */
public class FilterOrderConstant {

    public static final int ORDER_I18N_FILTER = 1;

    /**
     * 需要在SECURITY_FILTER之前
     */
    public static final int ORDER_FETCH_LOGIN_USER_FILTER = 2;

    public static final int ORDER_SECURITY_FILTER = 3;

    /**
     * 需要在审计日志之前
     */
    public static final int ORDER_OGNL_FILTER = 4;

    public static final int ORDER_AUDIT_LOG_FILTER = 5;

    public static final int ORDER_SERVICE_FILTER = 100;




}
