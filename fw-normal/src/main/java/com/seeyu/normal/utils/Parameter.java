package com.seeyu.normal.utils;

import lombok.Data;

/**
 * @author seeyu
 * @date 2019/6/17
 */

@Data
public class Parameter<T> {

    private T value;

    public Parameter(){

    }

    public Parameter(T value){
        this.value = value;
    }

}
