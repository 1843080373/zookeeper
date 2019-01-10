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
				Map<String,URL> URL=ZookeeperRegister.random(interfaceClass.getName());
				String protocol = (String) URL.keySet().toArray()[0];
				Protocol client =ProtocolFactory.getProtocol(protocol);
				Invocation invocation = new Invocation();
				invocation.setInterfaceName(interfaceClass.getName());
				invocation.setMethodName(method.getName());
				invocation.setParams(args);
				invocation.setParamTypes(method.getParameterTypes());
				System.out.println(JSONObject.toJSON(URL.get(protocol)));
				System.out.println(JSONObject.toJSON(invocation));
				try {
					Object rs = client.post(URL.get(protocol), invocation);
					System.out.println(rs);
					return rs;
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		});
	}
}
