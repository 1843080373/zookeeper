package com.rpc.diyrpc.consumer;

import java.util.List;

import com.ant.zk.api.entity.User;
import com.ant.zk.api.service.DemoService;
import com.ant.zk.api.service.HelloService;
import com.rpc.diyrpc.framework.ProxyFactory;

public class Consumer {

	public static void main(String[] args) {
		HelloService helloService=ProxyFactory.getProxy(HelloService.class);
		// System.out.println(helloService.sayHello("RPC"));
		helloService.save();
		/*DemoService demoService=ProxyFactory.getProxy(DemoService.class);
		
		
		List<User> rs=demoService.findUsers("张");
		for (User user : rs) {
			System.out.println(user);
		}
		demoService.save(new User("张三丰","110"));*/
	}
}
