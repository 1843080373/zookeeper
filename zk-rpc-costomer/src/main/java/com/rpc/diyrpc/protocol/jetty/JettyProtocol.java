package com.rpc.diyrpc.protocol.jetty;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.rpc.diyrpc.framework.Invocation;
import com.rpc.diyrpc.protocol.tomcat.Protocol;

public class JettyProtocol implements Protocol {

	public Object post(com.rpc.diyrpc.framework.URL urlParam, Invocation invocation) {
		HttpURLConnection connection=null;
		try {
			URL url = new URL("http", urlParam.getHonename(), urlParam.getPort(), "/");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			OutputStream outputStream = connection.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(outputStream);
			oos.writeObject(invocation);
			oos.flush();
			InputStream inputStream = connection.getInputStream();
			if(inputStream!=null) {
				ObjectInputStream ois=new ObjectInputStream(inputStream);
				return ois.readObject();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			connection.disconnect();
		}
		return null;
	}
}
