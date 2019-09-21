package com.seeyu.lang.utils;

/**
 * @author seeyu
 * @date 2019/7/24
 */
public class MessageFormat {

    private static final char FLAG_START = '{';
    private static final char FLAG_END = '}';

    public static String format(String pattern, Object ... arguments) {
        if(StringUtils.isBlank(pattern) || arguments == null || arguments.length == 0){
            return pattern;
        }
        else{
            return format2(pattern, arguments);
        }
    }


    private static String format2(String pattern, Object ... arguments) {
        StringBuilder sb = new StringBuilder(pattern.length());
        char[] cs = pattern.toCharArray();
        int l1 = cs.length, l2 = arguments.length;
        for(int i = 0, j = 0; i < l1; i++){
            char c = cs[i];
            if(c == FLAG_START && i+1 < l1 && cs[i+1] == FLAG_END && j < l2){
                sb.append(arguments[j++]);
                i++;
            }
            else{
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
