package com.seeyu.lang.utils;

/**
 * @author seeyu
 */
public class StringUtils {


    /**
     * 字符串数组转字符串
     * @param ss
     * @return
     */
    public static String StringArrays2String(String[] ss){
        return StringArrays2String(ss, null);
    }

    /**
     * 字符串数组转字符串 用指定的分隔符分割
     * @param ss
     * @param separator
     * @return
     */
    public static String StringArrays2String(String[] ss, String separator){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < ss.length; i++){
            String s = ss[i];
            if(i != 0 && separator != null){
                sb.append(separator);
            }
            sb.append(s);
        }
        return sb.toString();
    }


    /**
     * 验证 String 是否为空 (null或 length=0)
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        return str == null || str.length() == 0;
    }

    /**
     * 验证 String 是否不为空 (!null且 length!=0)
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str){
        return str != null && str.length() != 0;
    }


    /**
     * 验证 String 是否不为空白 (!null且 trim.length!=0)
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str){
        return !isBlank(str);
    }


    public static boolean isBlank(String str) {
        int strLen;
        if (str != null && (strLen = str.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }

    public static String trim(String str) {
        return str == null ? null : str.trim();
    }


    /**
     * 如果字符串为空就转为""
     * @return
     */
    public static String nullToBlank(String str){
        return str == null ? "" : str;
    }

    public static String trimToNull(String str) {
        String ts = trim(str);
        return isEmpty(ts) ? null : ts;
    }


    public static boolean equals(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equals(str2);
    }

    public static boolean equalsIgnoreCase(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equalsIgnoreCase(str2);
    }
}
