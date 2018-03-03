package com.test;

import java.util.Scanner;

public class FMTree {
	private int fNumber;
	private int mNumber;
	public int getfNumber() {
		return fNumber;
	}
	public int getmNumber() {
		return mNumber;
	}
	public static void main(String[] args) {
		FMTree fmTree=new FMTree();
		while(true){
			System.out.println("请输入要查询的FM树节点数据");
			Scanner scan=new Scanner(System.in);
			int n=scan.nextInt();
			System.out.println("第"+n+"个fNumber值是"+fmTree.fRecursion(n)+
					"第"+n+"个mNumber值是"+fmTree.mRecursion(n));
		}

	}
	
	public int fRecursion(int n){
		if(n==0){
			fNumber=1;
		}else{
			fNumber=n-mRecursion(fRecursion(n-1));
		}
		return fNumber;
	}
	public int mRecursion(int n){
		if(n==0){
			mNumber=0;
		}else{
			mNumber=n-fRecursion(mRecursion(n-1));
		}
		return mNumber;
	}
	
}
