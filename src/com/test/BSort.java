package com.test;

import java.util.List;

public class BSort {
	List list;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public List doSort(int[][] arr){
		int Maxx=arr[0].length;
		int Maxy=arr.length;
		for(int i=0;i<Maxx&&i<Maxy;i++){
			for(int num:arr[i]){
				
			}
		}
			
		
		/*for(int i=n;i>0;i--){
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
		}*/
		return list;
		
	}
}
