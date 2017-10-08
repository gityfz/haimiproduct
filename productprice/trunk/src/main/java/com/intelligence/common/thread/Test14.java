package com.intelligence.common.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test14 {
	public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Lock lock = new ReentrantLock(false);
        MyRunnable2 t1 = new MyRunnable2("张三", 2000,lock);
        MyRunnable2 t2 = new MyRunnable2("李四", 3600,lock);
        MyRunnable2 t3 = new MyRunnable2("王五", 2700,lock);
        MyRunnable2 t4 = new MyRunnable2("老张", 600,lock);
        MyRunnable2 t5 = new MyRunnable2("老牛", 1300,lock);
        MyRunnable2 t6 = new MyRunnable2("胖子", 800,lock);
        //执行各个线程
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);
        pool.execute(t6);
        //关闭线程池
        pool.shutdown();
	}
}

class MyRunnable2 implements Runnable {
	private static AtomicLong aLong = new AtomicLong(10000);        //原子量，每个线程都可以自由操作
	private String name;                //操作人
	private int x;                            //操作数额
	private Lock lock;
	
	MyRunnable2(String name, int x,Lock lock) {
	        this.name = name;
	        this.x = x;
	        this.lock = lock;
	}
	
	public void run() {
	        lock.lock();
	        System.out.println(name + "执行了" + x + "，当前余额：" + aLong.addAndGet(x));
	        lock.unlock();
	}
}
