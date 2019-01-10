package com.rpc.diyrpc.provider;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.support.RootBeanDefinition;

import com.ant.zk.api.service.DemoService;
import com.ant.zk.api.service.HelloService;
import com.rpc.diyrpc.framework.Configure;
import com.rpc.diyrpc.framework.RPCConfigure;
import com.rpc.diyrpc.framework.URL;
import com.rpc.diyrpc.provider.api.impl.DemoServiceImpl;
import com.rpc.diyrpc.provider.api.impl.HelloServiceImpl;
import com.rpc.diyrpc.register.ZookeeperRegister;

public class InitZKProvider {

	public static void main(String[] args) {
		//注册服务
		Configure conf=RPCConfigure.getConfigure();
		Map<String,URL> urls=new HashMap<>();
		String[] protocols=conf.getProtocolString().split(",");
		String[] hostname=conf.getHostname().split(",");
		String[] ports=conf.getPortString().split(",");
		if(protocols!=null&&protocols.length>0) {
			for (int i = 0; i < protocols.length; i++) {
				URL url=new URL(hostname[i], Integer.parseInt(ports[i]));
				urls.put(protocols[i],url);
			}
		}
		ZookeeperRegister.register(HelloService.class.getName(), urls, HelloServiceImpl.class.getName());
		ZookeeperRegister.register(DemoService.class.getName(), urls, DemoServiceImpl.class.getName());
		ZookeeperRegister.root();
		while(true) {
			
		}
	}
}
