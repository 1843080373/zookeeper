package com.rpc.diyrpc.protocol.jetty;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rpc.diyrpc.framework.Invocation;
import com.rpc.diyrpc.framework.URL;
import com.rpc.diyrpc.register.ZookeeperRegister;

public class JettyDispatchServlet extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.setStatus(HttpServletResponse.SC_OK);
			InputStream inputStream = req.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(inputStream);
			Invocation invocation = (com.rpc.diyrpc.framework.Invocation) ois.readObject();
			URL url=ZookeeperRegister.random(invocation.getInterfaceName());
			Class<?> inplClass=Class.forName(ZookeeperRegister.get(invocation.getInterfaceName(), url));
			System.out.println("选择了jetty协议");
			Method method=inplClass.getDeclaredMethod(invocation.getMethodName(), invocation.getParamTypes());
			Object result = method.invoke(inplClass.newInstance(), invocation.getParams());
			System.out.println(result);
			ObjectOutputStream oos = new ObjectOutputStream(resp.getOutputStream());
			oos.writeObject(result);
			oos.flush();
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
