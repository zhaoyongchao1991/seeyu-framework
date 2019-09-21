package com.seeyu.normal;

import org.springframework.boot.ResourceBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.core.io.InputStreamResource;

import java.io.InputStream;

/**
 * @author seeyu
 * @date 2019/6/11
 */
public class MySpringApplication {

    public static boolean ready;

    public static Class mainApplicationClass;

    public static void run(SpringApplication app) {
        MySpringApplication.mainApplicationClass = app.getMainApplicationClass();
        ResourceBanner banner = new ResourceBanner(new InputStreamResource(MySpringApplication.class.getResourceAsStream("/banner.txt")));
        app.setBanner(banner);
        MySpringApplication.ready = true;
    }
}
