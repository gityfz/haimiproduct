package com.intelligence.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerAop {
	
	/**
	 * AOP监控测试
	 * 
	 * 
	 * @param point
	 * @throws Throwable
	 */
	@Around("execution(* com.intelligence.autodev.controller.*Controller.action*(..))")
    public void paramPrintHandle(ProceedingJoinPoint point) throws Throwable {
		long a = System.currentTimeMillis();
		System.out.println("监控开始...");
        point.proceed(point.getArgs());
        long b = System.currentTimeMillis();
        System.out.println("监控结束..." + (b - a));
    }
	
//    @Before("execution(* com.intelligence.autodev.service.impl.TestServiceImpl.test*(..))")
//    public void permissionCheck(JoinPoint point) {
//        System.out.println("@Before：模拟权限检查...");
//    }
    
//    @AfterReturning(pointcut="execution(* com.abc.service.*.many*(..))", 
//        returning="returnValue")
//    public void log(JoinPoint point, Object returnValue) {
//        System.out.println("@AfterReturning：模拟日志记录功能...");
//        System.out.println("@AfterReturning：目标方法为：" + 
//                point.getSignature().getDeclaringTypeName() + 
//                "." + point.getSignature().getName());
//        System.out.println("@AfterReturning：参数为：" + 
//                Arrays.toString(point.getArgs()));
//        System.out.println("@AfterReturning：返回值为：" + returnValue);
//        System.out.println("@AfterReturning：被织入的目标对象为：" + point.getTarget());
//        
//    }
//    
//    @After("execution(* com.abc.service.*.many*(..))")
//    public void releaseResource(JoinPoint point) {
//        System.out.println("@After：模拟释放资源...");
//        System.out.println("@After：目标方法为：" + 
//                point.getSignature().getDeclaringTypeName() + 
//                "." + point.getSignature().getName());
//        System.out.println("@After：参数为：" + Arrays.toString(point.getArgs()));
//        System.out.println("@After：被织入的目标对象为：" + point.getTarget());
//    }
	
}
