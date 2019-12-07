package com.seeyu.fw.auth.config;

import com.seeyu.lang.utils.RSAUtils;
import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author seeyu
 * @date 2019/4/15
 */
@Data
@Configuration
public class TokenConfig {

    private RSAUtils.RsaKey rsaKey;
    private int expirationSeconds = 60 * 30;

    @PostConstruct
    public void init() throws Exception {
        rsaKey = RSAUtils.createtKey();
    }



}
