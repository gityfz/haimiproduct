package com.intelligence.common.reflect;

import java.lang.reflect.Method;

import org.springframework.stereotype.Component;

@Component
public class ReflectTools {

	/**
	 * 反射方法
	 * 
	 * @param owner
	 * @param methodName
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public Object invokeMethod(Object owner, String methodName, Object[] args) throws Exception {
	    @SuppressWarnings("rawtypes")
		Class ownerClass = owner.getClass();
	    @SuppressWarnings("rawtypes")
		Class[] argsClass = new Class[args.length];
	    for (int i = 0, j = args.length; i < j; i++) { 
	        argsClass[i] = args[i].getClass(); 
	    }
	    @SuppressWarnings("unchecked")
		Method method = ownerClass.getMethod(methodName,argsClass);
	    return method.invoke(owner, args); 
	}
	
}
