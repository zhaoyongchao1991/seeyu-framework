package com.seeyu.lang.utils;

import java.io.IOException;

/**
 * @author seeyu
 * @date 2019/5/10
 */
public class Base64Utils {

    public static String encode (byte[] bs){
        return new sun.misc.BASE64Encoder().encode(bs);
    }


    public static byte[] decode (String text) throws IOException {
        return new sun.misc.BASE64Decoder().decodeBuffer(text);
    }
}
