package com.rpc.diyrpc.protocol.netty;

import com.rpc.diyrpc.framework.Protocol;
import com.rpc.diyrpc.framework.URL;

public class NettyProtocol implements Protocol {
	 @Override
	public void start(URL url) {
		NettyServer server = new NettyServer();
		server.start(url.getHonename(), url.getPort());
	}

}
