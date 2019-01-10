package com.ant.zk.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//ServerSocket
public class ZKServerSocket implements Runnable{

	private int port = 10000;
	public void run() {
		ServerSocket serverSocket=null;
		try {
			serverSocket=new ServerSocket(port);
			System.out.println("serverSocket start in port:"+this.port);
			Socket socket=null;
			while (true) {
				socket=serverSocket.accept();
				new Thread(new ServerHandler(socket)).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(serverSocket!=null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	public ZKServerSocket(int port) {
		super();
		this.port = port;
	}
	
	public static void main(String[] args) {
		ZKServerSocket zk=new ZKServerSocket(18080);
		new Thread(zk).start();
	}

}
