package com.tisson.check;

import com.tisson.util.CheckIsRightTime;
import com.tisson.util.DateUtil;

import java.util.Date;
import java.util.Map;

/**
 * Created by xsjie on 2016-03-20.15:47
 */
public class Task implements Runnable {
    String loginUrl;
    Map<String, String> loginParam;
    String checkinUrl;
    Map<String, String> checkinparam;

    public void run() {
       try {
            Thread.sleep((long) (Math.random()*1000)*600);  // 随机睡眠0-10分钟 防止每天打卡时间一样
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (CheckIsRightTime.isRightTime(new Date())) {
            LoginCheckin loginCheckin = new LoginCheckin();
            loginCheckin.post(loginUrl, loginParam);
            loginCheckin.post(checkinUrl, checkinparam);
            loginCheckin.close();  // 完成一次调度后关闭 httpclient
        }else {
            System.out.println("非正确时间:"+ DateUtil.convertDateToString(new Date()));
        }
    }

    public Map<String, String> getLoginParam() {
        return loginParam;
    }

    public void setLoginParam(Map<String, String> loginParam) {
        this.loginParam = loginParam;
    }

    public String getCheckinUrl() {
        return checkinUrl;
    }

    public void setCheckinUrl(String checkinUrl) {
        this.checkinUrl = checkinUrl;
    }

    public Map<String, String> getCheckinparam() {
        return checkinparam;
    }

    public void setCheckinparam(Map<String, String> checkinparam) {
        this.checkinparam = checkinparam;
    }

    public String getLoginUrl() {

        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

}
