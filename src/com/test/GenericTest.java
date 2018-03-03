package com.test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class GenericTest {

	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		ArrayList<String> arr=new ArrayList<String>();
		arr.add("aa");
		Object o=new Object();
		arr.getClass().getMethod("add", Object.class).invoke(arr, o);
		Object st = arr.get(1);
		System.out.println(st);
	}

}
