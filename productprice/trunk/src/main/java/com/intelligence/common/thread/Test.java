package com.intelligence.common.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;





public class Test {
    public static void main(String[] args) throws ClientProtocolException, IOException {
        // 创建HttpClient实例     
        HttpClient httpclient = new DefaultHttpClient();  
        // 创建Get方法实例     
        HttpGet httpgets = new HttpGet("http://public-api.bj.vnd.tuniu.org/bb/user/scripts/StablePackPlan?dick=daoyongzhequsi&rows=select%201&flag=query");
        // http://public-api.bj.vnd.tuniu.org/bb/user/scripts/StablePackPlan?rows=SELECT * FROM bid_show_product WHERE show_date_id = 218 and start_city_code = 1602 and web_class in (163,82,2016,240,921,67,2047,100,98);&dick=daoyongzhequsi&flag=query
        HttpResponse response = httpclient.execute(httpgets);    
        HttpEntity entity = response.getEntity();    
        if (entity != null) {    
            InputStream instreams = entity.getContent();    
            String str = convertStreamToString(instreams);  
            System.out.println("Do something");   
            System.out.println(str);  
            // Do not need the rest    
            httpgets.abort();    
        }  
//    	System.out.println(MD5("TGZgVgvmjET27VzqRtUeagencyProductId10001apiKeyWhbG6NdCwgkt6vHPOfbasicInfo{\"agencyProductName\":\"\",\"festaName\":\"\u56fd\u5e86\u8282\",\"mainName\":\"\u6cd5\u56fd5\u65e5\",\"lastName\":\"\u4eb2\u5b50\u6e38\",\"catType\":\"6\",\"productMode\":\"1\",\"startCity\":\"\u4e0a\u6d77\",\"bookingCity\":\"\u5317\u4eac,\u4e0a\u6d77\",\"agencyManagerName\":\"\u9b4f\u6cd5\u5175\",\"recommend\":\"\u9ad8\u7aef\u5927\u6c14\u4e0a\u6863\u6b21\"}timestamp2015-08-13 18:13:00type0TGZgVgvmjET27VzqRtUe"));
    	
//            Thread t1 = new MyThread1();
//            Thread t2 = new Thread(new MyRunnable());
//            t2.start();
//            t1.start();
    }
    
    public static String convertStreamToString(InputStream is) {      
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));      
        StringBuilder sb = new StringBuilder();      
       
        String line = null;      
        try {      
            while ((line = reader.readLine()) != null) {  
                sb.append(line + "\n");      
            }      
        } catch (IOException e) {      
            e.printStackTrace();      
        } finally {      
            try {      
                is.close();      
            } catch (IOException e) {      
               e.printStackTrace();      
            }      
        }      
        return sb.toString();      
    }  
    
    public final static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       

        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    
}

class MyThread1 extends Thread {
    public void run() {
            for (int i = 0; i < 10; i++) {
                    System.out.println("线程1第" + i + "次执行！");
            }
    }
}

class MyRunnable implements Runnable {
    public void run() {
            for (int i = 0; i < 10; i++) {
                    System.out.println("线程2第" + i + "次执行！");
                    Thread.yield();
            }
    }
}
