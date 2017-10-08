package sleftest;

/**
 * @author Zhengyf
 *
 * @date 2017年3月1日
 */
public class JubinTest {
   
    public static void main(String[] args){
	 TestA a = new TestA();
	    TestA b = new TestA();
	    a.name = "1";
	    b= a.clone();
	    b.name = "2";
	    System.out.println(a.name);
	    System.out.println(b.name);
	
    }
}
