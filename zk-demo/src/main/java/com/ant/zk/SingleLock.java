package com.ant.zk;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SingleLock implements Runnable {
	OrderNumberGnnerator orderNumberGnnerator = new OrderNumberGnnerator();

	private Lock lock=new ReentrantLock();
	public void run() {
		//synchronized (this) {
			try {
				lock.lock();
				genOrderNumber();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				lock.unlock();
			}
		//}
	}

	public void genOrderNumber() {
		String number = orderNumberGnnerator.createOrderNumber();
		System.out.println(Thread.currentThread().getName() + "->" + "###订单号生成###" + number);
	}

	public static void main(String[] args) {
		System.out.println("模拟生产订单号开始");
		SingleLock singleLock=new SingleLock();
		for (int i = 0; i <100; i++) {
			new Thread(singleLock).start();
		}
	}

}
