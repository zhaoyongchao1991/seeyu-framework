package com.seeyu.i18n.core;

import com.seeyu.i18n.exception.MessageException;

/**
 * @author seeyu
 * @date 2019/3/8
 */
public interface II18nMessageProcessor {

    String getMessage(String key) throws MessageException;

}
