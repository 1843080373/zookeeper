package com.rpc.diyrpc.provider;

import java.util.HashMap;
import java.util.Map;

import com.rpc.diyrpc.framework.Configure;
import com.rpc.diyrpc.framework.Protocol;
import com.rpc.diyrpc.framework.ProtocolFactory;
import com.rpc.diyrpc.framework.RPCConfigure;
import com.rpc.diyrpc.framework.URL;

public class NettyStart {

	public static void main(String[] args) {
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
		String protocol = "netty";
		Protocol client = ProtocolFactory.getProtocol(protocol);
		client.start(urls.get(protocol));
	}
}
