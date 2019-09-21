package com.seeyu.mvc.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 初始化顺序问题，jsonUtils可能为null
 * @author seeyu
 * @date 2019/6/26
 */
@Deprecated
@Component
public class JsonUtils {

    private static JsonUtils jsonUtils;

    @Autowired
    private ObjectMapper objectMapper;

    public JsonUtils(){
        jsonUtils = this;
    }


    public static String bean2Json(Object object) throws JsonProcessingException {
        return jsonUtils.beanToJson(object);
    }


    public static T json2Bean(String json, Class<T> clazz) throws IOException {
        return jsonUtils.jsonToBean(json, clazz);
    }


    private String beanToJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    private T jsonToBean(String json, Class<T> clazz) throws IOException {
        return objectMapper.readValue(json, clazz);
    }



}
