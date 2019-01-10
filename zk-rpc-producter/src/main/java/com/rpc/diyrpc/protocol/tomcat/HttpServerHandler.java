package com.rpc.diyrpc.protocol.tomcat;

import java.io.EOFException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rpc.diyrpc.framework.Invocation;
import com.rpc.diyrpc.framework.URL;
import com.rpc.diyrpc.register.ZookeeperRegister;
public class HttpServerHandler {
	public void handle(HttpServletRequest req, HttpServletResponse resp) {
		try {
			System.out.println("选择了http协议");
			InputStream inputStream = req.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(inputStream);
			Invocation invocation = (com.rpc.diyrpc.framework.Invocation) ois.readObject();
			Map<String,Object> url=ZookeeperRegister.random(invocation.getInterfaceName());
			Class<?> inplClass=Class.forName((String) url.get("implName"));
			Method method=inplClass.getDeclaredMethod(invocation.getMethodName(), invocation.getParamTypes());
			Object result = method.invoke(inplClass.newInstance(), invocation.getParams());
			ObjectOutputStream oos = new ObjectOutputStream(resp.getOutputStream());
			oos.writeObject(result);
			oos.flush();
			oos.close();
		} catch (EOFException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
