package com.test;

public class Floyed {

	public static void main(String[] args) {
		int a[]={49,38,65,97,76,13,69,49};
		Floyed floyed=new Floyed();
		floyed.heapsort(a, a.length);
		for(int i=0;i<a.length;i++){
			System.out.println(a[i]);
		};

	}
	
	void sift(int r[],int k,int m){
		int i,j,x;
		i=k;j=2*i+1;x=r[i];
		while(j<=m){
			if(j<m&&r[j]<r[j+1])j++;
			if(x<r[j]){ //关键是用x做比较值
				r[i]=r[j];
//				r[j]=x;
				i=j;
				j=(j<<1)+1;}
			else{
					break;
			}
			r[i]=x;
		}
	}
	
	void heapsort(int r[],int n){
		int i;
		int x;
		for(i=(n>>1)-1;i>=0;i--)
			sift(r,i,n-1);
		for(i=n-1;i>=1;i--){
			x=r[0];
			r[0]=r[i];
			r[i]=x;
			sift(r,0,i-1);
		}
	}
}
