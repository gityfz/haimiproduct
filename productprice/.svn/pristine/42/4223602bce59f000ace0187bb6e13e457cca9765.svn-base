package com.intelligence.common.model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProAndCusQue {
//	BlockingQueue
	public static void main(String[] args) {
		BlockingQueue<Integer> bq = new LinkedBlockingQueue<Integer>();
		ProducerQue pq = new ProducerQue(bq);
		ConsumerQue cq = new ConsumerQue(bq);
		Thread pt = new Thread(pq);
		Thread ct = new Thread(cq);
		pt.start();
		ct.start();
	}
}

class ProducerQue implements Runnable {
	
	private BlockingQueue<Integer> blockingQueue = null;
	
	ProducerQue(BlockingQueue<Integer> bq) {
		this.blockingQueue = bq;
	}
	
	
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
                System.out.println("Produced: " + i);
                this.blockingQueue.put(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
	}
	
}

class ConsumerQue implements Runnable {
	
	private BlockingQueue<Integer> blockingQueue = null;
	
	ConsumerQue(BlockingQueue<Integer> bq) {
		this.blockingQueue = bq;
	}
	
	
	public void run() {
		for (int i = 0; i < 20; i++) {  // or while(true) {
			System.out.println("test: " + i);
			try {
				System.out.println("Consumed: " + this.blockingQueue.take());
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
	}
	
}