package com.test;

public class FormalParaTest {
	private int a;
	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	private String b;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int atest=5;
		System.out.println(atest);
		int a1test=atest;
		atest++;
		String btest="cc";
		String ctest=new String("cc");
		ctest=btest;
		btest=btest+"a";
		btest="cc"+"a";
		String dtest="cca";
		System.out.println(btest);
		System.out.println(dtest);
		System.out.println(btest==dtest);

/*		System.out.println(btest.hashCode());
		System.out.println(dtest.hashCode());
		
		StringBuilder builder1=new StringBuilder("builder");
		StringBuilder builder2=new StringBuilder("builder");
		builder2=builder1;*/

		
		FormalParaTest paraTest=new FormalParaTest();
		FormalParaTest paraTest1=new FormalParaTest();
		FormalParaTest paraTest2=paraTest;
/*		System.out.println(paraTest.hashCode());
		System.out.println(paraTest1.hashCode());
		System.out.println(paraTest2.hashCode());
		paraTest=paraTest1;
		System.out.println(paraTest.hashCode());
		System.out.println(paraTest2.hashCode());
		*/
		System.out.println();
		paraTest.add(atest, btest);

		System.out.println(a1test);
		/*System.out.println(atest);
		System.out.println(btest);
		System.out.println(ctest);
		System.out.println(ctest==btest);*/
/*		System.out.println(builder1);
		System.out.println(builder2);
		System.out.println(builder1==builder2);*/
	}
	
	public void add(int a,String b){
		a=a+1;
		b=b+"ab";
/*		System.out.println(a);
		System.out.println(b);*/
	}

}
