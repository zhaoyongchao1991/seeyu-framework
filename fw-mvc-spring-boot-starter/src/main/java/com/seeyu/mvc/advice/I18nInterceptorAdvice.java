package com.seeyu.mvc.advice;

import com.seeyu.core.utils.BaseJsonData;
import com.seeyu.i18n.core.I18nMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * @author seeyu
 * @date 2019/3/25
 */
@Slf4j
@ControllerAdvice
public class I18nInterceptorAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return MappingJackson2HttpMessageConverter.class.isAssignableFrom(converterType);
    }


    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        if(!(body instanceof BaseJsonData)){
            return body;
        }
        BaseJsonData jsonData = (BaseJsonData)body;
        jsonData.setMessage(I18nMessage.valueOf(jsonData.getMessage(), jsonData.getMessageArgs()));
        return body;
    }


}
