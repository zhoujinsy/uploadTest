package com.test;

import java.util.ArrayList;
import java.util.List;

public class ASort {
	int cum=0;
	List list= new ArrayList(); 
	
	public static void main(String[] args) {
		ASort aSort=new ASort();
		int x=4;
		aSort.doSort(x);
		System.out.println(aSort.list);
		
		
	}
	
	public List doSort(int n){
		for(int i=n;i>0;i--){
			if((n-i)%2==0){
				for(int j=0;j<i;j++){
					cum+=1;
					list.add(cum);
				};
				for(int k=0;k<i-1;k++){
					cum+=n;
					list.add(cum);
				}
			}else{
				for(int j=0;j<i;j++){
					cum-=1;
					list.add(cum);
				};
				for(int k=0;k<i-1;k++){
					cum-=n;
					list.add(cum);
				}
			}
		}
		return list;
		
	}
	

}
