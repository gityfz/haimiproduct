package sleftest;

import java.lang.reflect.Method;

public class TestClassLoader {
    public static void main(String[] args) throws Exception {
	TestA a = new TestA();
	TestA b = a;
//		.clone();
	Class<?> clz = a.getClass();
	Object o = clz.newInstance();
	System.out.println(a);
	System.out.print(b);
	Method m = clz.getMethod("foo", String.class);
//	for (int i = 0; i < 16; i++) {
//	    System.out.print(o);
//	    m.invoke(o, Integer.toString(i));
//	    
//	}
    }
}