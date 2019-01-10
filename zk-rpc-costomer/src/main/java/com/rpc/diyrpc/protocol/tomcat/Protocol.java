package com.rpc.diyrpc.protocol.tomcat;

import com.rpc.diyrpc.framework.Invocation;
import com.rpc.diyrpc.framework.URL;

public interface Protocol {

	
	public Object post(URL url,Invocation invocation);
}
