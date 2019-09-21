package com.seeyu.normal.listener;

import com.seeyu.normal.MySpringApplication;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author seeyu
 * @date 2019/6/11
 */
public class ValidateAppEnviromentStartupListener implements ApplicationListener<ApplicationStartingEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        if(!MySpringApplication.ready){
            MySpringApplication.run(event.getSpringApplication());
        }
    }


}
