package com.intelligence.common.task;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.intelligence.business.price.service.IPriceRateService;
import com.intelligence.common.exception.PowerException;

@Component
public class PriceTask {

	@Resource
	private IPriceRateService iPriceRateService;
	
	/**
	 * 刷新价格汇率
	 */
	//@Scheduled(cron = "0 * * * * ?")
	public void refreshPriceRate() {
		try {
			iPriceRateService.refreshCurrentRate();
		} catch (PowerException e) {
			e.printStackTrace();
		}
	}
	
}
