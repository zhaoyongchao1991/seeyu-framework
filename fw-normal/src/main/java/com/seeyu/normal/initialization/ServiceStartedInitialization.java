package com.seeyu.normal.initialization;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author seeyu
 * @date 2019/6/12
 */
@Component
public class ServiceStartedInitialization implements ApplicationRunner{

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("服务启动完成！");
    }
}
