package com.tisson.check;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.jar.Attributes;

/**
 * Created by xsjie on 2016-03-20.15:59
 */
@Component("schedule")
public class Schedule {
    @Scheduled(cron = "0 55 8,17 ? * MON-FRI")
    public void taskSchedule() {
        Manage.service();
//        System.out.println(System.currentTimeMillis()+"be invoking");
    }


}
