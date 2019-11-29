package com.seeyu.fw.auth.constant.option;

import com.seeyu.core.constant.ActivationState;
import com.seeyu.fw.auth.constant.message.SystemMessageConstant;
import com.seeyu.normal.constant.options.OptionGroup;

/**
 * @author seeyu
 * @date 2019/4/26
 */
public class GlobalOptions {

    /**
     * 激活状态
     */
    public static OptionGroup activationOptions = new OptionGroup(2);
    static{
        activationOptions.add(String.valueOf(ActivationState.getActiveState()), SystemMessageConstant.SYSTEM_ACTIVATION_ACTIVE);
        activationOptions.add(String.valueOf(ActivationState.getDisableState()),SystemMessageConstant.SYSTEM_ACTIVATION_DISABLE);
    }

}