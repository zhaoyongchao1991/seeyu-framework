package com.seeyu.mvc.audit.log.interceptor.helper;

import com.seeyu.mvc.ognl.RequestContextOgnlParser;
import lombok.Data;
import ognl.OgnlException;

/**
 * @author seeyu
 * @date 2019/6/27
 */
@Data
public class AuditLogOgnlHelper {


    public static String getValue(String expression) throws OgnlException {
        return RequestContextOgnlParser.parse(wrapExpression(expression));
    }


    private static String wrapExpression(String expression){
        if(expression.startsWith("#")){
            return "#" + RequestContextOgnlParser.DEFAULT_REQUEST_BODY_PARAMETER_KEY + "." + expression.substring(1);
        }
        return expression;
    }
}
