package com.ant.attribute.lock;

import java.util.concurrent.CountDownLatch;

import org.I0Itec.zkclient.ZkClient;

//重构重复代码，将重复代码交给子类执行
public abstract class ZookeeperAbstractLock implements Lock {

	// zk链接地址
	private static final String CONNECTION_STR = "127.0.0.1:2181";
	protected ZkClient zkClient = new ZkClient(CONNECTION_STR);
	protected static final String PATH = "/lock";
	protected CountDownLatch countDownLatch = null;
	public void getLock() {
		if (tryLock()) {
			System.out.println("####得到锁###");
		} else {
			waitLock();
			getLock();
		}
	}

	protected abstract void waitLock();

	protected abstract boolean tryLock();

	public void unLock() {
		if (zkClient != null) {
			zkClient.close();
			System.out.println("释放锁");
		}
	}

}
