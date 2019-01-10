package com.rpc.diyrpc.protocol.tomcat;

import com.rpc.diyrpc.framework.Protocol;
import com.rpc.diyrpc.framework.URL;

public class HttpProtocol implements Protocol {

	@Override
	public void start(URL url) {
		// 启动
		HttpServer server = new HttpServer();
		server.start(url.getHonename(), url.getPort());
	}

}
