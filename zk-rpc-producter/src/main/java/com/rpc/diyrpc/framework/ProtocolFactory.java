package com.rpc.diyrpc.framework;

import com.rpc.diyrpc.protocol.jetty.JettyProtocol;
import com.rpc.diyrpc.protocol.netty.NettyProtocol;
import com.rpc.diyrpc.protocol.tomcat.HttpProtocol;

public class ProtocolFactory {

	public static Protocol getProtocol(String type) {
		Protocol protocol = null;
		switch (type) {
		case ProviderProtocol.netty:
			protocol=new NettyProtocol();
			break;
		case ProviderProtocol.jetty:
			protocol = new JettyProtocol();
			break;
		case ProviderProtocol.http:
			protocol = new HttpProtocol();
			break;
		default:
			protocol = new HttpProtocol();
			break;
		}
		return protocol;
	}
}
