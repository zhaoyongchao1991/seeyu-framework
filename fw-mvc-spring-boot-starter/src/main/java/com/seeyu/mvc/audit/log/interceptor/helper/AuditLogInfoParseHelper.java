package com.seeyu.mvc.audit.log.interceptor.helper;

import com.seeyu.lang.utils.StringUtils;
import com.seeyu.mvc.audit.log.AuditLogger;
import com.seeyu.mvc.common.model.AuditLogHolderI;
import ognl.OgnlException;

/**
 * @author seeyu
 * @date 2019/6/27
 */
public class AuditLogInfoParseHelper {

    private final String OGNL_START = "{";
    private final String OGNL_END = "}";

    private AuditLogHolderI auditLog;

    public AuditLogInfoParseHelper(AuditLogHolderI auditLog){
        this.auditLog = auditLog;
    }

    public String parse() throws OgnlException {
        if (this.auditLog == null || StringUtils.isBlank(this.auditLog.getInfo())) {
            return null;
        }
        return this.parseExpression(this.auditLog.getInfo(), 0);
    }

    private String parseExpression(String str, int index) throws OgnlException {
        int start = str.indexOf(OGNL_START);
        int end = str.indexOf(OGNL_END);
        if(start == -1 || end == -1){
            return str;
        }
        String text = str.substring(start, end+1);
        String expression = text.substring(1,text.length()-1);
        String value = this.getValue(expression, index);
        if(this.isBlankExpression(expression)){
            index++;
        }
        return this.parseExpression(str.substring(0, start) + value + str.substring(end+1), index);
    }

    private String getValue(String expression, int index) throws OgnlException {
        if(this.isBlankExpression(expression)){
            return this.getArgument(index);
        }
        else{
            return AuditLogOgnlHelper.getValue(expression);
        }
    }

    private String getArgument(int index){
        Object[] arguments = AuditLogger.getArguments();
        if(arguments == null || index >= arguments.length){
            return "";
        }
        Object ob = arguments[index];
        if(ob == null){
            return "";
        }
        return String.valueOf(ob);
    }

    private boolean isBlankExpression(String expression){
        return StringUtils.isBlank(expression);
    }

}
