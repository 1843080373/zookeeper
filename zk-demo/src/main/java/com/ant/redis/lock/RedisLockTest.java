package com.ant.redis.lock;

import java.util.UUID;

import com.ant.zk.OrderNumberGnnerator;
import com.crossoverjie.distributed.lock.redis.RedisLock;

import redis.clients.jedis.Jedis;

public class RedisLockTest implements Runnable {

	OrderNumberGnnerator orderNumberGnnerator = new OrderNumberGnnerator();
	RedisLock redisLock = build();

	public RedisLock build() {
		RedisLock redisLock = new RedisLock();
		Jedis jedisCluster = new Jedis("127.0.0.1", 6379); 
		redisLock.setJedis(jedisCluster);
		return redisLock;
	}

	public void use() {
		String key = "key";
		String request = UUID.randomUUID().toString();
		try {
			boolean locktest = redisLock.tryLock(key, request);
			if (!locktest) {
				System.out.println("locked error");
				return;
			} // do something
			orderNumberGnnerator.createOrderNumber();
		} finally {
			redisLock.unlock(key, request);
		}

	}

	public void run() {
		use();
	}

	public void genOrderNumber() {
		String number = orderNumberGnnerator.createOrderNumber();
		System.out.println(Thread.currentThread().getName() + "->" + "###订单号生成###" + number);
	}

	public static void main(String[] args) {
		System.out.println("模拟生产订单号开始");
		for (int i = 0; i < 100; i++) {
			RedisLockTest singleLock = new RedisLockTest();
			new Thread(singleLock).start();
		}
	}

}
