package com.intelligence.common.http;

public class MySingleton {
	//使用volatile关键字保其可见性  
    volatile private static MySingleton instance = null;  
      
    private MySingleton(){}  
       
    public static MySingleton getInstance() {  
        if(instance == null){//懒汉式
		    synchronized (MySingleton.class) {  
		        if(instance == null){//二次检查  
		            instance = new MySingleton();  
		        }  
		    }  
		}  
        return instance;  
    }  
    
}
