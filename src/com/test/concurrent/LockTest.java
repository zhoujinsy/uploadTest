package com.test.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
	public static int ticket=100;
	private Lock lock=new ReentrantLock();
	private int wakeNum=1;
	public Condition condition1=lock.newCondition();
	public Condition condition2=lock.newCondition();
	public Condition condition3=lock.newCondition();
	
	public static void main(String[] args) {
		LockTest lockTest=new LockTest();
		MyThread1 myThread1=new MyThread1(lockTest);
		MyThread2 myThread2=new MyThread2(lockTest);
		MyThread3 myThread3=new MyThread3(lockTest);
		myThread1.start();
		myThread2.start();
		myThread3.start();


	}
	
	public void sellTicket1(Thread thread){
		lock.lock();
		try {
			while(ticket>0){
				while(wakeNum!=1){
					condition1.await();
				}
				System.out.println("线程名"+thread.getName()+"获得锁");
				System.out.println("票数"+ticket);
				ticket--;
				wakeNum=2;
				condition2.signal();
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			lock.unlock();
		}
	}
	
	public void sellTicket2(Thread thread){
		lock.lock();
		try {
			while(ticket>0){
				while(wakeNum!=2){
					condition2.await();
				}
				System.out.println("线程名"+thread.getName()+"获得锁");
				System.out.println("票数"+ticket);
				ticket--;
				wakeNum=3;
				condition3.signal();
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			lock.unlock();
		}
	}
	
	public void sellTicket3(Thread thread){
		lock.lock();
		try {
			while(ticket>0){
				while(wakeNum!=3){
					condition3.await();
				}
				System.out.println("线程名"+thread.getName()+"获得锁");
				System.out.println("票数"+ticket);
				ticket--;
				wakeNum=1;
				condition1.signal();
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			lock.unlock();
		}
	}
}

class MyThread1 extends Thread{
	LockTest lockTest;
	public MyThread1(LockTest lockTest){
		this.lockTest = lockTest;
	}
	@Override
	public void run() {
		lockTest.sellTicket1(Thread.currentThread());
	}
}

class MyThread2 extends Thread{
	LockTest lockTest;
	public MyThread2(LockTest lockTest){
		this.lockTest = lockTest;
	}
	@Override
	public void run() {
		lockTest.sellTicket2(Thread.currentThread());
	}
}

class MyThread3 extends Thread{
	LockTest lockTest;
	public MyThread3(LockTest lockTest){
		this.lockTest = lockTest;

	}
	@Override
	public void run() {
		lockTest.sellTicket3(Thread.currentThread());
	}
}