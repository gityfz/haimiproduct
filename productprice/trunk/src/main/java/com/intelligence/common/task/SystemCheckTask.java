/**
 * 系统自检测试定时任务java文件
 */
package com.intelligence.common.task;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.intelligence.business.price.dao.IPriceUnitDao;
import com.intelligence.business.price.service.IPriceUnitService;

/**
 * 系统自检测试定时任务类
 * 
 * @author sunhao
 * @date 2014-06-03 11:32:45
 */
@Component
public class SystemCheckTask {
@Resource
private  IPriceUnitService iPriceUnitService;
	
	
//	@Scheduled(cron = "0/5 * 9-18 * * ?")    //cron表达式依次为秒，分，小时，天，月，星期（与天互斥），（年）
	public void test() {
//		System.out.println("系统定时自检。。。");
		System.out.println(iPriceUnitService.getPriceUnit(2));
	}
	
}
