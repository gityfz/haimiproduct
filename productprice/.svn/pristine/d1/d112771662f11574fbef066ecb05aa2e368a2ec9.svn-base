/**
 * 
 */
package com.intelligence.common.cache;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author p-sunhao
 *
 */
@Component
public class AutoContext implements Serializable {

	/**
	 * 生成的序列号
	 */
	private static final long serialVersionUID = 3416856090993684527L;
	
	/**
	 * httpRequest
	 */
	@Resource
	private HttpServletRequest httpRequest;
	
	/**
	 * httpResponse
	 */
	@Resource
	private HttpServletResponse httpResponse;
	
	/**
	 * 单例容器
	 */  
    private static ApplicationContext singletonContext = null;
    
    /**
     * 获取spring容器
     * 
     * @return
     */
    public ApplicationContext getAutoContext() {
    	if (null == singletonContext) {
    		singletonContext = WebApplicationContextUtils.getWebApplicationContext(httpRequest.getSession().getServletContext());	
    	}
    	return singletonContext;
    }
	
}
