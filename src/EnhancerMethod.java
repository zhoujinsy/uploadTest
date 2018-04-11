
public class EnhancerMethod{
	public void logBefore(Class<?> clazz){
		System.out.println(clazz.getName()+"已经被加载,增强方法即将被执行");
	}
	
	public int doubleBigger(int c){
		return 2*c;
	}
	
	public void logAfter(Class<?> clazz){
		System.out.println(clazz.getName()+"已经被加载,增强方法执行完毕");
	}
}
