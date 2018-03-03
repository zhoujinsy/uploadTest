package com.test;

public class AccountImpl implements Account {
	private double balance;
	@Override
	public Object deposit(double value) {
        System.out.println("deposit: " + value);  
        balance += value; 
		return null;
	}

	@Override
	public Object getBalance() {
		// TODO Auto-generated method stub
		System.out.println("balance="+balance);
		return null;
	}

}
