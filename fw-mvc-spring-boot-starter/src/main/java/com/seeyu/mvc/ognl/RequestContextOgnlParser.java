package com.seeyu.mvc.ognl;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

/**
 * @author seeyu
 * @date 2019/6/28
 */
public class RequestContextOgnlParser {

    public final static String DEFAULT_REQUEST_BODY_PARAMETER_KEY = "_parameter";


    public static String parse(String expression) throws OgnlException {
        return ognlValue2String(parseValue(expression));
    }

    public static Object parseValue(String expression) throws OgnlException {
        OgnlContext context = RequestContextOgnlContextHolder.getRequestContextOgnlContext();
        return Ognl.getValue(Ognl.parseExpression(expression), context, context.getRoot());
    }


    private static String ognlValue2String(Object value){
        if(value == null){
            return "";
        }
        if(value.getClass().isArray()){
            Object[] values = (Object[])value;
            if(values.length == 0){
                return "";
            }
            else if(values.length == 1){
                return ognlValue2String(values[0]);
            }
            else{
                StringBuilder sb = new StringBuilder("[");
                for(Object ob : (Object[])value){
                    if(ob == null){
                        continue;
                    }
                    sb.append(ognlValue2String(ob)).append(",");
                }
                return sb.substring(0, sb.length()-1) + "]";
            }
        }
        return String.valueOf(value);
    }

}
