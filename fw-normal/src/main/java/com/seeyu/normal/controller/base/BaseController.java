package com.seeyu.normal.controller.base;

import com.seeyu.lang.utils.AESUtils;
import com.seeyu.lang.utils.MD5;
import com.seeyu.mvc.common.constant.ResponseCode;
import com.seeyu.mvc.constant.I18nMessageKeyConstant;
import com.seeyu.mvc.controller.base.AbstractController;
import com.seeyu.normal.constant.MvcConstant;
import com.seeyu.normal.utils.JsonData;

/**
 * @author seeyu
 */
public abstract class BaseController extends AbstractController {

    public static String passwordEncrypt(String password) throws Exception {
        return MD5.encrypt(AESUtils.encrypt(password, MvcConstant.USER_PASSWORD_AES_KEY, MvcConstant.USER_PASSWORD_AES_KEY));
    }

    public static boolean passwordValidate(String target, String password) throws Exception {
        return target.equals(passwordEncrypt(password));
    }

    @Override
    protected JsonData SUCCESS(){
        return JsonData.SUCCESS().setCode(ResponseCode.SUCCESS_CODE).setMessage(I18nMessageKeyConstant.SERVER_TIPS_ALERT_SUCCESSFUL_OPERATION);
    }

    @Override
    protected JsonData ERROR(){
        return JsonData.ERROR().setCode(ResponseCode.SERVER_EXCEPTION).setMessage(I18nMessageKeyConstant.SERVER_EXCEPTION_ALERT_SERVER_EXCEPTION);
    }


}
