package com.seeyu.normal.utils.result;

import com.seeyu.core.exception.AlertException;
import com.seeyu.core.exception.ServiceException;
import com.seeyu.core.utils.ServiceData;
import lombok.Getter;

/**
 * @author seeyu
 * @date 2019/11/25
 */
public class ResultData<T> extends ServiceData<T> {

    @Getter
    private boolean alert = false;

    protected ResultData(boolean success, boolean alert){
        super(success);
        this.alert = alert;
    }

    protected ResultData(ResultData<T> resultData){
        super(resultData.success);
        this.setMessage(resultData.getMessage(), resultData.getMessageArgs())
        .setData(resultData.getData());
        this.alert = resultData.isAlert();
    }

    public static ResultData SUCCEED(){
        return new ResultData(true, false);
    }

    public static ResultData FAILED(){
        return new ResultData(false, false);
    }

    public static ResultData ALERTED(){
        return new ResultData(false, true);
    }

    public static ResultData SUCCEED(ResultData rd){
        return new ResultData(rd);
    }

    public static ResultData FAILED(ResultData rd){
        return new ResultData(rd);
    }

    public static ResultData ALERTED(ResultData rd){
        return new ResultData(rd);
    }


    @Override
    public ResultData<T> raise() throws ServiceException {
        if(!this.success){
            if(this.isAlert()){
                throw new AlertException(this.getMessage(), this.getMessageArgs());
            }
            else{
                throw new ServiceException(this.getMessage(), this.getMessageArgs());
            }
        }
        return this;
    }


    public boolean isAlert() {
        return alert;
    }

    public ResultData<T> setMessage(String message) {
        super.setMessage(message);
        return this;
    }

    @Override
    public ResultData<T> setMessage(String message, Object...messageArgs) {
        super.setMessage(message, messageArgs);
        return this;
    }

    @Override
    public ResultData<T> setData(T data) {
        super.setData(data);
        return this;
    }
}
