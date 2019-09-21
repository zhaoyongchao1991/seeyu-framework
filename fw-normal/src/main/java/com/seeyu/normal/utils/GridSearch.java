package com.seeyu.normal.utils;

import com.seeyu.core.utils.BasePage;
import lombok.Data;

/**
 * @author seeyu
 * @date 2019/6/17
 */
@Data
public class GridSearch<T, P extends BasePage> {

    private T model;
    private P page;

    public GridSearch(){

    }

    public GridSearch(T model, P page){
        this.model = model;
        this.page = page;
    }

}
