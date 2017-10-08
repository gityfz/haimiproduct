package com.intelligence.autodev.service.impl;

import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.intelligence.autodev.dao.ITestDao;
import com.intelligence.autodev.service.ITestService;

@Service
public class TestServiceImpl implements ITestService {

	@Resource
	private ITestDao iTestDao;
	
	public void test() {
		iTestDao.test();
	}
	
	public void testMemcache() throws TimeoutException, InterruptedException, MemcachedException {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-xmemcached.xml");
		  MemcachedClient client = (MemcachedClient) context.getBean("xmemcachedClient");
		  System.out.println(client);
		  client.add("name",100,"张三");
//		  System.out.println(client.get("name"));
//		  client.delete("name");
//		  System.out.println(client.get("name"));
	}
	
	public int test2(Integer a) {
		return a;
	}
	
}
