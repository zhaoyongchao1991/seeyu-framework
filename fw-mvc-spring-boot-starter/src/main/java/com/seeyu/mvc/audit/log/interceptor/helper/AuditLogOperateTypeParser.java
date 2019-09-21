package com.seeyu.mvc.audit.log.interceptor.helper;


import com.seeyu.mvc.audit.log.constant.AuditLogOperateTypeConstant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author seeyu
 * @date 2019/7/2
 */
public class AuditLogOperateTypeParser {


    private static final Map<String, String> optTypeMapping = new HashMap<>();
    static{
        optTypeMapping.put("insert", AuditLogOperateTypeConstant.INSERT_TYPE);
        optTypeMapping.put("save", AuditLogOperateTypeConstant.INSERT_TYPE);
        optTypeMapping.put("add", AuditLogOperateTypeConstant.INSERT_TYPE);

        optTypeMapping.put("delete", AuditLogOperateTypeConstant.DELETE_TYPE);
        optTypeMapping.put("remove", AuditLogOperateTypeConstant.DELETE_TYPE);

        optTypeMapping.put("update", AuditLogOperateTypeConstant.UPDATE_TYPE);
        optTypeMapping.put("modify", AuditLogOperateTypeConstant.UPDATE_TYPE);
        optTypeMapping.put("edit", AuditLogOperateTypeConstant.UPDATE_TYPE);

        optTypeMapping.put("select", AuditLogOperateTypeConstant.QUERY_TYPE);
        optTypeMapping.put("query", AuditLogOperateTypeConstant.QUERY_TYPE);
        optTypeMapping.put("get", AuditLogOperateTypeConstant.QUERY_TYPE);
        optTypeMapping.put("search", AuditLogOperateTypeConstant.QUERY_TYPE);
        optTypeMapping.put("grid", AuditLogOperateTypeConstant.QUERY_TYPE);
        optTypeMapping.put("show", AuditLogOperateTypeConstant.QUERY_TYPE);

        optTypeMapping.put("login", AuditLogOperateTypeConstant.LOGIN_TYPE);

        optTypeMapping.put("logout", AuditLogOperateTypeConstant.LOGOUT_TYPE);

        optTypeMapping.put("download", AuditLogOperateTypeConstant.DOWNLOAD_TYPE);

        optTypeMapping.put("upload", AuditLogOperateTypeConstant.UPLOAD_TYPE);
    }

    private String methodName;

    public AuditLogOperateTypeParser(String methodName){
        assert methodName != null;
        this.methodName = methodName;
    }

    public String parse(){
        for(Map.Entry<String, String> e : optTypeMapping.entrySet()){
           if( this.methodName.startsWith(e.getKey())){
               return e.getValue();
           }
        }
        return null;
    }

}
