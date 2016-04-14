package com.tisson.check;

import com.tisson.util.CustomizedPropertyConfigurer;
import org.springframework.core.task.TaskTimeoutException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xsjie on 2016-03-20.16:17
 */
public class Manage {
    public static final String REQUESTPARAMNAME = "account"  ;
    public static final String REQUESTPARAMPD = "password" ;
    static ExecutorService executors =  Executors.newCachedThreadPool();
    static String loginUrl;
    static String[] usernameAndPassword;
//    static String[] loginParam;
    static String checkinUrl;
    static String[] checkinParam;

    static {
        loginUrl = (String) CustomizedPropertyConfigurer.getContextProperty("loginUrl");
        usernameAndPassword = ((String) CustomizedPropertyConfigurer.getContextProperty("usernameAndPassword")).trim().split(",");
//        loginParam = ((String) CustomizedPropertyConfigurer.getContextProperty("loginParam")).trim().split(",");
        checkinUrl = (String) CustomizedPropertyConfigurer.getContextProperty("checkinUrl");
        checkinParam = ((String) CustomizedPropertyConfigurer.getContextProperty("checkinParam")).trim().split(",");
    }

    public static void service() {
        for (String userInfo : usernameAndPassword) {
            Task task = new Task();
            task.setLoginUrl(loginUrl);
            Map<String, String> allloginParam = new HashMap<String, String>();  // 登录需要的参数，一般情况为username和password
            String username = userInfo.split(":")[0];
            String password = userInfo.split(":")[1] ;
            allloginParam.put(REQUESTPARAMNAME,username) ;
            allloginParam.put(REQUESTPARAMPD, password);
//           if (loginParam != null && loginParam.length >0  )
//            {
//                for (int i = 0 ;i<loginParam.length;i++) {
//                    allloginParam.put(loginParam[i].split(":")[0],loginParam[i].split(":")[1]) ;
//                }
//            }
            task.setLoginParam(allloginParam);
            task.setCheckinUrl(checkinUrl);
            Map<String, String> allcheckinParam = new HashMap<String, String>(); // 打卡时提交的参数，根据情况来定
            if (checkinParam != null && checkinParam.length >0 )
            {
                for (int i = 0 ;i<checkinParam.length;i++) {
                    allcheckinParam.put(checkinParam[i].split(":")[0],checkinParam[i].split(":")[1]) ;
                }
            }
            task.setCheckinparam(allcheckinParam);
            executors.execute(task); ;

        }

    }

}
