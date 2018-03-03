import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


public class EnhancerHandler implements InvocationHandler {
	MathTest mathTest;
	EnhancerMethod enhancerMethod;
	public EnhancerHandler(MathTest mathTest,EnhancerMethod enhancerMethod){
		this.mathTest=mathTest;
		this.enhancerMethod=enhancerMethod;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		enhancerMethod.logBefore(mathTest.getClass());
		int little =(int) method.invoke(mathTest, args);
		int doubleBigger = enhancerMethod.doubleBigger(little);
		enhancerMethod.logAfter(mathTest.getClass());
		return doubleBigger;
	}

}
