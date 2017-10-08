package com.intelligence.common.http;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyThread extends Thread{  
    
    @Override  
    public void run() {   
//    	MySingleton.getInstance().hashCode();
    	MySingletonStatic.getInstance().hashCode();
//        System.out.println(MySingleton.getInstance().hashCode());  
    }  
      
    public static void main(String[] args) throws InterruptedException {   
    	
    	// 开始的倒数锁 
        final CountDownLatch begin = new CountDownLatch(1);  

        // 结束的倒数锁 
        final CountDownLatch end = new CountDownLatch(10000);  

        // 十名选手 
        final ExecutorService exec = Executors.newFixedThreadPool(10000);  
        long pre = System.currentTimeMillis();
        for (int index = 0; index < 10000; index++) {
            final int NO = index + 1;  
            Runnable run = new Runnable() {
                public void run() {  
                    try {  
                        // 如果当前计数为零，则此方法立即返回。
                        // 等待
                        begin.await();    
                        MySingleton.getInstance().hashCode();
//                        MySingletonStatic.getInstance().hashCode();
//                        System.out.println("No." + NO + " arrived");  
                    } catch (InterruptedException e) {  
                    } finally {  
                        // 每个选手到达终点时，end就减一
                        end.countDown();
                    }  
                }  
            };  
            exec.submit(run);
        }  
        System.out.println("Game Start");  
        // begin减一，开始游戏
        begin.countDown();  
        // 等待end变为0，即所有选手到达终点
        end.await();  
        System.out.println("Game Over");  
        exec.shutdown();
    	
        System.out.println(System.currentTimeMillis() - pre);
    	
          
//        MyThread[] mts = new MyThread[10000];  
//        for(int i = 0 ; i < mts.length ; i++){  
//            mts[i] = new MyThread();  
//        }  
//        for (int j = 0; j < mts.length; j++) {  
//            mts[j].start();  
//        }
//        mts[j].isAlive();
        
    }  
} 
