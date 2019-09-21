package com.seeyu.mvc.configuration.auto.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author seeyu
 * @date 2019/3/12
 */
@ConfigurationProperties("seeyu.fw.i18n")
@Data
public class I18nConfigProperties {

    private String basename = "mess";
    private String encoding = "utf-8";

}
