package com.rpc.diyrpc.protocol.jetty;

import com.rpc.diyrpc.framework.Protocol;

public class JettyProtocol implements Protocol {

	@Override
	public void start(com.rpc.diyrpc.framework.URL url) {
		// 启动
		JettyServer server = new JettyServer();
		server.start(url.getHonename(), url.getPort());
		System.out.println("jetty started");
	}

}
