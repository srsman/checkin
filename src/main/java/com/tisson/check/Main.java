package com.tisson.check;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by xsjie on 2016-03-20.20:04
 */
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml") ;
        ((Schedule)context.getBean("schedule")).taskSchedule();
    }
}
