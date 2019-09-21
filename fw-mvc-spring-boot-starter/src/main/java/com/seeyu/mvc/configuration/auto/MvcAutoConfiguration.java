package com.seeyu.mvc.configuration.auto;

import com.seeyu.mvc.advice.GlobalMvcAdvice;
import com.seeyu.mvc.advice.GlobalMvcExceptionAdvice;
import com.seeyu.mvc.configuration.FilterConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;

/**
 * @author seeyu
 * @date 2019/6/26
 */
@ImportAutoConfiguration({
        FilterConfiguration.class,
        GlobalMvcExceptionAdvice.class,
        GlobalMvcAdvice.class
})
public class MvcAutoConfiguration {
}

