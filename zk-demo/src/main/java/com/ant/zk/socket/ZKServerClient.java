package com.ant.zk.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ZKServerClient {

	//所有的客户端
	private static List<String> servers=new ArrayList<String>();
	
	public static void main(String[] args) {
		initServsers();
		ZKServerClient zk=new ZKServerClient();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String name;
			try {
				name=br.readLine();
				if("exit".equals(name)) {
					System.exit(-1);
				}
				zk.send(name);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void initServsers() {
		servers.clear();
		servers.add("127.0.0.1:18080");
	}
	
	private static String getServser() {
		return servers.get(0);
	}
	private void send(String name) {
		String server=ZKServerClient.getServser();
		String[] cfg=server.split(":");
		Socket socket=null;
		BufferedReader in = null;
		try {
			socket=new Socket(cfg[0], Integer.parseInt(cfg[1]));
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			socket.getOutputStream().write(name.getBytes("UTF-8"));
			socket.shutdownOutput();
			while (true) {
				String response = in.readLine();
				if (response==null) {
					break;
				}else if(response.length()>0) {
					System.out.println("客户端收到:" + name);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(socket!=null)
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				} 
		}
	}
}
