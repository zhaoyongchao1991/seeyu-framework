package com.seeyu.mvc.configuration.auto;

import com.seeyu.mvc.utils.JsonUtils;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;

/**
 * @author seeyu
 * @date 2019/6/26
 */
@ImportAutoConfiguration({
        JsonUtils.class
})
public class OtherAutoConfiguration {
}
