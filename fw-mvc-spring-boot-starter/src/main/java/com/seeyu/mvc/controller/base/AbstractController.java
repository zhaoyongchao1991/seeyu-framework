package com.seeyu.mvc.controller.base;


import com.seeyu.core.utils.BaseJsonData;

/**
 * @author seeyu
 */
public abstract class AbstractController {

    protected abstract BaseJsonData SUCCESS();

    protected abstract BaseJsonData ERROR();


}
