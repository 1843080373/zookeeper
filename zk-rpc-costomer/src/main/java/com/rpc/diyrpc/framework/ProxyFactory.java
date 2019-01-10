package com.rpc.diyrpc.framework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.rpc.diyrpc.protocol.tomcat.Protocol;
import com.rpc.diyrpc.register.ZookeeperRegister;

public class ProxyFactory {
	@SuppressWarnings("unchecked")
	public static <T> T getProxy(@SuppressWarnings("rawtypes") final Class interfaceClass){
		return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[] {interfaceClass}, new InvocationHandler() {
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				Map<String,Object> map=ZookeeperRegister.random(interfaceClass.getName());
				String protocol = (String)map.get("protocol");
				Protocol client =ProtocolFactory.getProtocol(protocol);
				Invocation invocation = new Invocation();
				invocation.setInterfaceName(interfaceClass.getName());
				invocation.setMethodName(method.getName());
				invocation.setParams(args);
				invocation.setParamTypes(method.getParameterTypes());
				try {
					System.out.println("map="+JSONObject.toJSONString(map));
					System.out.println("invocation="+JSONObject.toJSONString(invocation));
					Object rs = client.post((URL)map.get("url"), invocation);
					
					return rs;
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		});
	}
}
