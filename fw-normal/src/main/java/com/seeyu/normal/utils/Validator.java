package com.seeyu.normal.utils;


import com.seeyu.core.utils.Assert;
import com.seeyu.normal.constant.options.OptionGroup;

/**
 * @author seeyu
 * @date 2019/1/25
 */
public class Validator {


    /**
     * value 是否存在于选项组 options中
     * @param options
     * @param value
     */
    public static void inOptions(OptionGroup options, String value){
        if(value == null){
            return;
        }
        Assert.notNull(options.findOptionByValue(value), "未知选项: " + value);
    }




}
