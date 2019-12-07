package com.seeyu.fw.auth.utils;

import com.seeyu.fw.auth.config.AuthConfig;
import com.seeyu.lang.utils.AESUtils;
import com.seeyu.normal.utils.Md5Utils;

/**
 * @author seeyu
 * @date 2019/12/4
 */
public class PasswordTool {

    private final static String KEY = "audit!#$%^&*(#0@";

    public static String passwordEncode(String password){
        return Md5Utils.encrypt(password);
    }

    public static String passwordEncrypt(String password) {
        return Md5Utils.encrypt(AESUtils.encrypt(password, KEY, KEY), AuthConfig.PASSWORD_SALT);
    }

    public static String passwordPlaintextEncrypt(String password) {
        return passwordEncrypt(passwordEncode(password));
    }

    public static boolean passwordEquals(String target, String password) {
        return target.equals(passwordEncrypt(password));
    }
}
