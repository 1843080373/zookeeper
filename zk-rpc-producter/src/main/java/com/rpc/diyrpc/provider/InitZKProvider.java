package com.rpc.diyrpc.provider;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.ant.zk.api.service.DemoService;
import com.ant.zk.api.service.HelloService;
import com.rpc.diyrpc.framework.Configure;
import com.rpc.diyrpc.framework.RPCConfigure;
import com.rpc.diyrpc.framework.URL;
import com.rpc.diyrpc.provider.api.impl.DemoServiceImpl;
import com.rpc.diyrpc.provider.api.impl.HelloServiceImpl;
import com.rpc.diyrpc.register.ZookeeperRegister;

public class InitZKProvider {
	// {服务名：{URL:实现类}}
	private static Map<String,Map<URL, Map<String,String>>> REGISTER = new HashMap<>();

	public static void main(String[] args) {
		// 注册服务
		Configure conf = RPCConfigure.getConfigure();
		Map<String, URL> urls = new HashMap<>();
		String[] protocols = conf.getProtocolString().split(",");
		String[] hostname = conf.getHostname().split(",");
		String[] ports = conf.getPortString().split(",");
		if (protocols != null && protocols.length > 0) {
			for (int i = 0; i < protocols.length; i++) {
				URL url = new URL(hostname[i], Integer.parseInt(ports[i]));
				urls.put(protocols[i], url);
			}
		}
		ZookeeperRegister.register(HelloService.class.getName(), urls, HelloServiceImpl.class.getName());
		ZookeeperRegister.register(DemoService.class.getName(), urls, DemoServiceImpl.class.getName());
		
		ZookeeperRegister.writDataToZK();
		while (true) {
			
		}
	}

	@SuppressWarnings("unused")
	private static void register(String interf, Map<String, URL> urls, String implName) {
		Set<String> keys=urls.keySet();
		Map<URL, Map<String,String>> map=new HashMap<>();
		for (String protocol : keys) {
			URL url=urls.get(protocol);
			Map<String, String> mapP=new HashMap<>();
			mapP.put("protocol", protocol);
			mapP.put("implName", implName);
			map.put(url, mapP);
		}
		REGISTER.put(interf, map);
	}
}
