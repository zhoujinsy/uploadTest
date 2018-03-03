package com.test;

import com.test.anotation.Baz;
import com.test.anotation.Foo;

public class AnotationTest implements Foo{

	public static void main(String[] args) {
		Baz baz = AnotationTest.class.getAnnotation(Baz.class);
		System.out.println(String.class.getClassLoader());
		
		if(baz==null){
			System.out.println("接口的注解不能被继承");
		}else{
			System.out.println("当前类上的注解"+baz.value());
		}
		Class<?>[] interfaces = AnotationTest.class.getInterfaces();
		for(int i=0;i<interfaces.length;i++){
			if (interfaces[i]==Foo.class){
				System.out.println("反射获得接口上的注解"+Foo.class.getAnnotation(Baz.class).value());
			}
		}
		
	}

}

