package com.seeyu.fw.auth.exception;

/**
 * @author seeyu
 * @date 2019/12/10
 */
public class EntityAlreadyInUsedException extends Exception{

    public EntityAlreadyInUsedException() {
        super();
    }

    public EntityAlreadyInUsedException(String message) {
        super(message);
    }

}
