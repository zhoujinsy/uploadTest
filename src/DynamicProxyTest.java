import java.lang.reflect.Proxy;



public class DynamicProxyTest {

	public static void main(String[] args) {
		MathTest mathTest=new AddTest();
		EnhancerMethod enhancerMethod=new EnhancerMethod();
		MathTest newProxyInstance = (MathTest)Proxy.newProxyInstance(mathTest.getClass().getClassLoader(),
				mathTest.getClass().getInterfaces(), new EnhancerHandler(mathTest,enhancerMethod));

		System.out.println(newProxyInstance.add(2, 3));
		
	}
	
	

}
