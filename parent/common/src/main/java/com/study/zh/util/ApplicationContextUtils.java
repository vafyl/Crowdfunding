package com.study.zh.util;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtils implements ApplicationContextAware {

    public static ApplicationContext applicationContext;


    //接口注入
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        ApplicationContextUtils.applicationContext = applicationContext;

    }
}
