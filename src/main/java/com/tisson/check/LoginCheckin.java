package com.tisson.check;

import java.io.IOException;
import java.util.*;

import com.tisson.util.DateUtil;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class LoginCheckin {

    CloseableHttpClient httpclient = HttpClients.createDefault();


    public static void main(String[] args) {

        LoginCheckin loginCheckin = new LoginCheckin();
        // 构建 paramMap 存放需要 POST 提交的参数
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("account", "xiongsj");
        paramMap.put("password", "888888");
        String url = "http://172.16.24.151/teamwork/www/index.php?m=user&f=login"; // 请求 URL

        loginCheckin.post(url, paramMap); // 登录

        paramMap.clear();  // 清空参数后放入新的参数
        paramMap.put("checkin", "checkin");
        url = "http://172.16.24.151/teamwork/www/index.php?m=my&f=checkin";

        loginCheckin.post(url, paramMap); // 签到或签退

    }


    /**
     * 发送 post 请求
     */
    public void post(String url, Map<String, String> paramMap) {
        HttpPost httppost = new HttpPost(url);
        // 创建参数队列
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        for (Iterator<String> iterator = paramMap.keySet().iterator(); iterator.hasNext(); ) {
            String paramName = iterator.next();
            String paramValue = paramMap.get(paramName);
            formparams.add(new BasicNameValuePair(paramName, paramValue));
        }
        UrlEncodedFormEntity uefEntity;
        try {
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httppost.setEntity(uefEntity);
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();

              /*  if (entity != null) {
                    System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));
                }*/
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 发送 get请求
     */
    public void get(String url) {
        System.out.println("get " + url);
        try {
            HttpGet httpget = new HttpGet(url);
            // 执行get请求.
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    System.out.println("Response content: " + EntityUtils.toString(entity, "utf-8"));
                }
                System.out.println("------------------------------------");
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void close()
    {
        System.out.println(DateUtil.convertDateToLongString(new Date()));
        try {
            httpclient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}