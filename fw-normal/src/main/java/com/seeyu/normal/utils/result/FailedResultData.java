package com.seeyu.normal.utils.result;

/**
 * @author seeyu
 * @date 2019/11/25
 */
public class FailedResultData<T> extends ResultData<T> {

    public FailedResultData() {
        super(false, false);
    }
}
