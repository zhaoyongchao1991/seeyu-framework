package com.seeyu.normal.utils;

import com.seeyu.core.utils.BaseJsonData;
import lombok.ToString;

/**
 * @author seeyu
 * @date 2019/4/25
 */
@ToString
public class JsonData extends BaseJsonData {

    public JsonData() {
        super(false);
    }

    public JsonData(boolean success) {
        super(success);
    }


    public static JsonData SUCCESS() {
        return new JsonData(true);
    }


    public static JsonData ERROR() {
        return new JsonData(false);
    }


    @Override
    public JsonData setSuccess(boolean success) {
        super.setSuccess(success);
        return this;
    }


    @Override
    public JsonData setData(Object data) {
        //没有进行分页操作的GridData
//        if(data instanceof GridData && ((GridData)data).getPageNum() == null){
//            data = ((GridData)data).getList();
//        }
        super.setData(data);
        return this;
    }



    @Override
    public JsonData setException(Exception exception) {
        super.setException(exception);
        return this;
    }


    @Override
    public JsonData setMessage(String message, Object...messageArgs) {
        super.setMessage(message, messageArgs);
        return this;
    }


    @Override
    public JsonData setExceptionMessage(String exceptionMessage) {
        super.setExceptionMessage(exceptionMessage);
        return this;
    }


    @Override
    public JsonData setCode(Integer code) {
        super.setCode(code);
        return this;
    }
}
