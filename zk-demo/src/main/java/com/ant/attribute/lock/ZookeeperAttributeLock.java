package com.ant.attribute.lock;

import java.util.concurrent.CountDownLatch;

import org.I0Itec.zkclient.IZkDataListener;
	
public class ZookeeperAttributeLock extends ZookeeperAbstractLock {

	@Override
	protected void waitLock() {
		// 使用事件监听来获取锁，节点被删除时候
		IZkDataListener iZkDataListener = new IZkDataListener() {
			// 当节点被删除
			public void handleDataDeleted(String dataPath) throws Exception {
				// 唤醒
				if (countDownLatch != null) {
					countDownLatch.countDown();
				}
			}

			public void handleDataChange(String dataPath, Object data) throws Exception {

			}
		};
		
		zkClient.subscribeDataChanges(PATH, iZkDataListener);
		if (zkClient.exists(PATH)) {
			// 创建信号量
			countDownLatch = new CountDownLatch(1);
			try {
				// 等待唤醒
				countDownLatch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		zkClient.unsubscribeDataChanges(PATH, iZkDataListener);
	}

	@Override
	protected boolean tryLock() {
		try {
			zkClient.createEphemeral(PATH);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
