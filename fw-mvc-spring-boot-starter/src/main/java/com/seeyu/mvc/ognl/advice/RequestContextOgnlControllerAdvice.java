package com.seeyu.mvc.ognl.advice;

import com.seeyu.mvc.ognl.RequestContextOgnlContextHolder;
import com.seeyu.mvc.ognl.RequestContextOgnlParser;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * 这里初始化OGNL 把request parameter和request body 参数放到OGNL中
 *
 * @author seeyu
 * @date 2019/6/28
 */
@ControllerAdvice
@Component
public class RequestContextOgnlControllerAdvice extends RequestBodyAdviceAdapter implements Filter {


    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        this.putOgnlContextRequestBodyParameter(body);
        return body;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        this.setOgnlContextRoot(request.getParameterMap());

        filterChain.doFilter(servletRequest, servletResponse);

        RequestContextOgnlContextHolder.clear();
    }

    @Override
    public void destroy() {

    }

    private void putOgnlContextRequestBodyParameter(Object parameter){
        RequestContextOgnlContextHolder.putOgnlContextValue(RequestContextOgnlParser.DEFAULT_REQUEST_BODY_PARAMETER_KEY, parameter);
    }

    private void setOgnlContextRoot(Object root){
        RequestContextOgnlContextHolder.setOgnlRoot(root);
    }

}
