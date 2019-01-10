package com.rpc.diyrpc.consumer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.I0Itec.zkclient.ZkClient;

import com.alibaba.fastjson.JSONObject;
import com.rpc.diyrpc.framework.Configure;
import com.rpc.diyrpc.framework.RPCConfigure;
import com.rpc.diyrpc.framework.URL;

public class GetAllNode {
	private static final String ROOT_NODE="/zk_root";
	private static final String SELECT_PROTOCOL="/select_protocol";
	private static Map<String, Map<String, Map<URL, String>>> REGISTER_LIST = new HashMap<>();
	private static ZkClient zk = null;
	private static Configure conf=null;
	static {
		conf=RPCConfigure.getConfigure();
		String connection=conf.getZookeeperHostname()+":"+conf.getZookeeperPort();
		zk = new ZkClient(connection);
	}
	private static Map<String, Map<String, Map<URL, String>>> readFromZK() {
		return zk.readData(ROOT_NODE);
	}
	public static void main(String[] args) {
		while (true) {

			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					REGISTER_LIST = readFromZK();
					System.out.println(new SimpleDateFormat("yyyy-MM-dd-hh-ss-mm").format(new Date())+JSONObject.toJSON(REGISTER_LIST));
				}
			}).start();
			
		}
	}
}
