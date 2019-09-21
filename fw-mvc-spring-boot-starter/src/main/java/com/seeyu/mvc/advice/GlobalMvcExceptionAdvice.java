package com.seeyu.mvc.advice;

import com.seeyu.core.exception.AlertRuntimeException;
import com.seeyu.core.exception.AuthorityAuthenticationFailException;
import com.seeyu.core.exception.LoginAuthenticationFailException;
import com.seeyu.core.utils.BaseJsonData;
import com.seeyu.i18n.core.I18nMessage;
import com.seeyu.mvc.common.constant.ResponseCode;
import com.seeyu.mvc.constant.I18nMessageKeyConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author seeyu
 * @date 2019/3/7
 */
@ControllerAdvice
@Slf4j
public class GlobalMvcExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(value = AlertRuntimeException.class)
    public BaseJsonData errorHandler(AlertRuntimeException e) {
        String message = I18nMessage.valueOf(e.getMessage(), e.getArguments());
        log.warn(message);
        log.debug("一般请求异常", e);
        return BaseJsonData.ERROR().setCode(ResponseCode.NORMAL_EXCEPTION).setMessage(message);
    }


    @ResponseBody
    @ExceptionHandler(value = LoginAuthenticationFailException.class)
    public BaseJsonData errorHandler(LoginAuthenticationFailException e) {
        log.warn("未登录或SESSION过期");
        log.debug("未登录或SESSION过期", e);
        return BaseJsonData.ERROR().setCode(ResponseCode.LOGIN_AUTHENTICATION_FAIL).setMessage(I18nMessageKeyConstant.SERVER_EXCEPTION_ALERT_LOGIN_AUTHENTICATION_EXCEPTION);
    }


    @ResponseBody
    @ExceptionHandler(value = AuthorityAuthenticationFailException.class)
    public BaseJsonData errorHandler(AuthorityAuthenticationFailException e) {
        log.warn("权限认证失败");
        log.debug("权限认证失败", e);
        return BaseJsonData.ERROR().setCode(ResponseCode.AUTHORITY_AUTHENTICATION_FAIL).setMessage(I18nMessageKeyConstant.SERVER_EXCEPTION_ALERT_AUTHENTICATION_FAILED_EXCEPTION);
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public BaseJsonData errorHandler(Exception ex) {
        log.error("系统异常", ex);
        BaseJsonData jsonData = BaseJsonData.ERROR().setCode(ResponseCode.SERVER_EXCEPTION).setMessage(I18nMessageKeyConstant.SERVER_EXCEPTION_ALERT_SERVER_EXCEPTION);
        if (log.isDebugEnabled()) {
            jsonData.setExceptionMessage(ex.getMessage());
        }
        if(log.isTraceEnabled()){
            jsonData.setException(ex);
        }
        return jsonData;
    }

}