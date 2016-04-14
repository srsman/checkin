package com.tisson.util;

import com.tisson.check.Main;

import java.util.Date;

/**
 * Created by xsjie on 2016-03-22.15:18
 */
public class CheckIsRightTime {
    // 检查打卡时间是否正取
    public static boolean isRightTime(Date date) {
//        Date date = new Date();
        if(DateUtil.isWeekend(date))
        {
            return false ;
        }
        int hour = DateUtil.getHour(date) ;
        int min = DateUtil.getMinute(date) ;
        if (hour==8 && min>54)
        {
            return true ;
        }
        if (hour==9 && min<10)
        {
            return true ;
        }
        if (hour==17 && min >54)
        {
            return true ;
        }
        if (hour == 18 && min<10)
        {
            return true ;
        }
        return  false ;
    }

   /* public static void main(String[] args) throws Exception{
        Date date = DateUtil.convertStringToDate("2016-03-28 18:09:59") ;
        System.out.println(isRightTime(date));
    }*/
}
