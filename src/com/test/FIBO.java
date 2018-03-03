package com.test;

public class FIBO {
	private int fiboNumber;
	public int getFiboNumber() {
		return fiboNumber;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FIBO fibo=new FIBO();
		System.out.println(fibo.fiboRecursion(2));

	}
	
	public int fiboRecursion(int n){
		if(n<=0){
			System.out.println("参数不合法");
			return 0;
		}
		
		if(1==n||2==n){
			fiboNumber=1;
		}else{
			fiboNumber=fiboRecursion(n-1)+fiboRecursion(n-2);	
		}
		
		
		return fiboNumber;
		
	}

}
