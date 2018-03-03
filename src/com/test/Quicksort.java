package com.test;

import java.util.Random;

public class Quicksort {

	public static void main(String[] args) {
//		int a[]={49,38,65,97,76,13,69,49,66,83,99,138,1,7,55,6,200};
		int[] a = new int[10000];
		Random r = new Random();
		for(int i=0;i<10000;i++){
			a[i]=r.nextInt(20000);
		}
		Quicksort quicksort=new Quicksort();
		quicksort.qsort(a, 0, a.length-1);
		for(int i=0;i<a.length;i++){
			System.out.println(a[i]);
		}

	}
	
	int partion(int r[],int i,int j){
		int x;
//		x=r[i];
		while(i<j){
			while(i<j && r[i]<=r[j])j--;
			if(i<j){
				x=r[i];
				r[i]=r[j];
				r[j]=x;
			}
			
			while(i<j && r[i]<=r[j])i++;
			if(i<j){
				x=r[j];
				r[j]=r[i];
				r[i]=x;
			}
		}
		return i;
	}
	
	void qsort(int r[],int l,int h){
		int m;
		if(l<h){
			m=partion(r,l,h);
			qsort(r,l,m-1);
			qsort(r,m+1,h);
		}
	
	}

}
