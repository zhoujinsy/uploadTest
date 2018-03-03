package com.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ExampleInvocationHandler implements InvocationHandler {
		Object anyObject;
		public ExampleInvocationHandler(Object anyObject){
			this.anyObject=anyObject;
		}
		
	    @Override  
	    public Object invoke (Object proxy, Method method, Object[] args) throws Throwable {  
	  
	        // simplified method checks, would need to check the parameter count and types too  
	        if ("deposit".equals(method.getName())) {  
	        	method.invoke(anyObject, args);
	            return proxy; // here we use the proxy to return 'this'  
	        }  
	        if ("getBalance".equals(method.getName())) {  
	        	return method.invoke(anyObject, args);
	        }  
	        return null;  
	    }  
}
