package com.ant.attribute.lock;

import com.ant.zk.OrderNumberGnnerator;

public class ZKLockTest implements Runnable {
	
	OrderNumberGnnerator orderNumberGnnerator = new OrderNumberGnnerator();

	private Lock lock = new ZookeeperAttributeLock();

	public void run() {
		try {
			lock.getLock();
			genOrderNumber();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unLock();
		}
	}

	public void genOrderNumber() {
		String number = orderNumberGnnerator.createOrderNumber();
		System.out.println(Thread.currentThread().getName() + "->" + "###订单号生成###" + number);
	}

	public static void main(String[] args) {
		System.out.println("模拟生产订单号开始");
		for (int i = 0; i < 100; i++) {
			ZKLockTest singleLock = new ZKLockTest();
			new Thread(singleLock).start();
		}
	}

}
