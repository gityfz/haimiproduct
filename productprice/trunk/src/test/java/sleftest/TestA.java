package sleftest;

/**
 * @author Zhengyf
 *
 * @date 2017年2月22日
 */
public class TestA implements Cloneable{
    public String name;
    public void foo(String name) {
	System.out.println("Hello, " + name);
    }
    public TestA clone(){
	TestA o = null;
	try{
	    o=(TestA)super.clone();
	}catch(Exception e ){e.printStackTrace();}
	return o;
    }
    
}
