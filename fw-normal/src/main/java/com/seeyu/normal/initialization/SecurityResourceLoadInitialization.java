package com.seeyu.normal.initialization;

import com.seeyu.mvc.security.filter.SecurityFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author seeyu
 * @date 2019/11/19
 */
@Slf4j
@Configuration
public class SecurityResourceLoadInitialization {

    @Autowired
    private SecurityFilter securityFilter;

    @PostConstruct
    public void run(){
        this.securityFilter.reLoadResources();
    }

}
