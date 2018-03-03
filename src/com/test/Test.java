package com.test;

import java.io.Serializable;
import java.lang.reflect.Proxy;

public class Test {

	public static void main(String[] args) {
		Account at=new AccountImpl();
		Account account = (Account) Proxy.newProxyInstance(Account.class.getClassLoader(), new Class[] {Account.class, Serializable.class},  
			    new ExampleInvocationHandler(at));  
			  
			// method chaining for the win!  
		((Account)account.deposit(5000)).deposit(4000);
		

	}

}
