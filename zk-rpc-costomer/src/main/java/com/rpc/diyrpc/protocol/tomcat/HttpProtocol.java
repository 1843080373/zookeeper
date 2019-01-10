package com.rpc.diyrpc.protocol.tomcat;

import java.io.EOFException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.rpc.diyrpc.framework.Invocation;

public class HttpProtocol implements Protocol {

	public Object post(com.rpc.diyrpc.framework.URL urlParam, Invocation invocation) {
		try {
			URL url = new URL("http", urlParam.getHonename(), urlParam.getPort(), "/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			OutputStream outputStream = connection.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(outputStream);
			oos.writeObject(invocation);
			oos.flush();
			oos.close();
			InputStream inputStream = connection.getInputStream();
			if(inputStream!=null) {
				ObjectInputStream ois=new ObjectInputStream(inputStream);
				return ois.readObject();
			}
		} catch (EOFException e) {
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
