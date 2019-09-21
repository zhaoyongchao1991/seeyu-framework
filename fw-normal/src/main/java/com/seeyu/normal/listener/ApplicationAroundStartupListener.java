package com.seeyu.normal.listener;

import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author seeyu
 * @date 2019/6/12
 */
public class ApplicationAroundStartupListener implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof ApplicationFailedEvent){
            System.err.println("服务启动失败，请检查日志！");
        }
    }


}