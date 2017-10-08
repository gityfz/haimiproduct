package sleftest;

/**
 * @author Zhengyf
 *
 * @date 2017年1月14日
 */
public class SingletonPatternDemo {

    public static void main(String[] args) {

	// 不合法的构造函数

	// 编译时错误：构造函数 SingleObject() 是不可见的
	// SingleObject object = new SingleObject();

	// 获取唯一可用的对象
	System.out.println(SingleObject.testint++);
	System.out.println(SingleObject.testint++);
	System.out.println(SingleObject.testint++);
	SingleObject object = SingleObject.getInstance();

	SingleObject m = SingleObject.getInstance();
	SingleObject n = SingleObject.getInstance();

	// 显示消息
	object.showMessage();
	System.out.println(object);
	System.out.println(m);
	System.out.println(n);
    }
}