package com.seeyu.core.constant;

/**
 * 激活状态
 * @author seeyu
 */
public enum ActivationState {

    ACTIVE(1),DISABLE(0);

    private int value;

    private ActivationState(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static int getActiveState(){
        return ACTIVE.getValue();
    }

    public static int getDisableState(){
        return DISABLE.getValue();
    }

    public static boolean isActiveState(Integer state){
        return state == null ? false : state.equals(1);
    }

}
