package com.intelligence.common.model;

/**
 * 原始型的生产者消费者
 * 
 * @author sunhao2
 *
 */
public class ProAndCusOri {

	public static void main(String[] args) {
		StackBasket s = new StackBasket();  
	    Producer p = new Producer(s);  
	    Consumer c = new Consumer(s);  
	    Thread tp = new Thread(p);  
	    Thread tc1 = new Thread(new Consumer(s));
	    Thread tc2 = new Thread(new Consumer(s));
//	    Thread tc3 = new Thread(c);
	    tp.start();  
	    tc1.start();
	    tc2.start();
//	    tc3.start();
	}
	
}
 
/**
 * 产品实体
 * 
 * @author sunhao2
 *
 */
class Product {  
    private int id;  
      
    Product(int id){  
        this.id = id;  
    }  
  
    public String toString(){  
        return "Product :" + this.id;  
    }  
}  

/**
 * 模拟队列
 * 
 * @author sunhao2
 *
 */
class StackBasket  
{  
	Product pro[] = new Product[10];  
	
    int index = 0;  
      
    /**  
    * show 生产方法. 
    * show 该方法为同步方法，持有方法锁； 
    * show 首先循环判断满否，满的话使该线程等待，释放同步方法锁，允许消费； 
    * show 当不满时首先唤醒正在等待的消费方法，但是也只能让其进入就绪状态， 
    * show 等生产结束释放同步方法锁后消费才能持有该锁进行消费 
    * @param m 元素 
    * @return 没有返回值  
    */   
    public synchronized void push(Product p){  
        try{  
            while(this.index == pro.length){  
                System.out.println("!!!!!!!!!生产满了!!!!!!!!!");  
                this.wait();  
            }  
        }catch(InterruptedException e){  
            e.printStackTrace();  
        }catch(IllegalMonitorStateException e){  
            e.printStackTrace();  
        } finally {
        	this.notify();
        }  
          
        pro[index] = p;  
        index++;  
        System.out.println("生产了：" + p.toString() + " 共" + index + "个馒头");  
    }  
  
    /**  
    * show 消费方法 
    * show 该方法为同步方法，持有方法锁 
    * show 首先循环判断空否，空的话使该线程等待，释放同步方法锁，允许生产； 
    * show 当不空时首先唤醒正在等待的生产方法，但是也只能让其进入就绪状态 
    * show 等消费结束释放同步方法锁后生产才能持有该锁进行生产 
    * @param b true 表示显示，false 表示隐藏  
    * @return 没有返回值  
    */   
    public synchronized Product pop(){  
        try{  
            while(index == 0){  
                System.out.println("!!!!!!!!!消费光了!!!!!!!!!");  
                this.wait();  
            } 
        }catch(InterruptedException e){  
            e.printStackTrace();  
        }catch(IllegalMonitorStateException e){  
            e.printStackTrace();  
        } finally {
        	this.notify();
        }
        index--;  
        System.out.println("消费了：---------" + pro[index] + " 共" + index + "个馒头");  
        return pro[index];  
    }  
}  

class Producer implements Runnable {  
    private StackBasket ss = null;  
    
    Producer(StackBasket ss){  
        this.ss = ss;  
    }  
  
    /**  
    * show 生产进程.  
    */   
    public void run(){  
        for(int i = 0;i < 40;i++){  
        	Product p = new Product(i);  
            ss.push(p);  
            // 增加随机性  
            try{  
                Thread.sleep((int)(Math.random()*50));  
            }catch(InterruptedException e){  
                e.printStackTrace();  
            }  
        }
        System.out.println("生产结束！");
    }  
}  
  
class Consumer implements Runnable  
{  
	private StackBasket ss = null;  
    
    Consumer(StackBasket ss){  
        this.ss = ss;  
    }  
  
    /**  
    * show 消费进程. 
    */   
    public void run(){  
        for(int i = 0;i < 20;i++){  
            ss.pop();  
            // 增加随机性  
            try{  
                Thread.sleep((int)(Math.random()*100));  
            }catch(InterruptedException e){  
                e.printStackTrace();  
            }  
        }  
        System.out.println("消费结束！");
    }  
}  