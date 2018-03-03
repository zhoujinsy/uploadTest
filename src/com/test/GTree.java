package com.test;

import java.util.Scanner;

public class GTree {
	private int gNumber;
	public int getgNumber() {
		return gNumber;
	}
	public static void main(String[] args) {
		GTree gTree=new GTree();
		while(true){
			System.out.println("请输入要查询的G树节点数据");
			Scanner scan=new Scanner(System.in);
			int n=scan.nextInt();
			System.out.println("第"+n+"个g树节点值是"+gTree.GRecursion(n));
		}

	}
	public int GRecursion(int n){
		if(n==0){
			gNumber=0;
		}else{
			gNumber=n-GRecursion(GRecursion(n-1));
		}
		
		return gNumber;
	}
}
