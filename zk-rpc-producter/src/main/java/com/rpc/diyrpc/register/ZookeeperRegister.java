package com.rpc.diyrpc.register;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.I0Itec.zkclient.ZkClient;

import com.alibaba.fastjson.JSONObject;
import com.rpc.diyrpc.framework.Configure;
import com.rpc.diyrpc.framework.RPCConfigure;
import com.rpc.diyrpc.framework.URL;
public class ZookeeperRegister {

	private static final String ROOT_NODE="/zk_root";
	private static final String SELECT_PROTOCOL="/select_protocol";
	//{服务名：{URL:实现类}}
	private static Map<String,Map<URL, Map<String,String>>> REGISTER = new HashMap<>();
	private static ZkClient zk = null;
	static final int SESSION_TIMEOUT = 5000000;
	private static Configure conf=null;
	static {
		conf=RPCConfigure.getConfigure();
		String connection=conf.getZookeeperHostname()+":"+conf.getZookeeperPort();
		zk = new ZkClient(connection,SESSION_TIMEOUT);
		createNode(ROOT_NODE);
	}
	public static void register(String interfaceName,Map<String,URL> urls,String implClass) {
		Set<String> keys=urls.keySet();
		Map<URL, Map<String,String>> map=new HashMap<>();
		for (String protocol : keys) {
			URL url=urls.get(protocol);
			Map<String, String> mapP=new HashMap<>();
			mapP.put("protocol", protocol);
			mapP.put("implName", implClass);
			map.put(url, mapP);
		}
		REGISTER.put(interfaceName, map);
	}
	
	private static void createNode(String nodePath) {
		if(zk.exists(nodePath)){
			zk.deleteRecursive(nodePath);
		}
		zk.createPersistent(nodePath,true);
	}
	
	public static void writDataToZK() {
		System.out.println(JSONObject.toJSON(REGISTER));
		zk.writeData(ROOT_NODE, REGISTER);
	}
	public static String get(String interfaceName,URL url) {
		Map<String,Map<URL, Map<String,String>>> REGISTER=zk.readData(ROOT_NODE);
		System.out.println(JSONObject.toJSON(REGISTER));
		return REGISTER.get(interfaceName).get(url).get("implName");
	}
	

	public static Map<String,Object> random(String interfaceName) {
		return zk.readData(SELECT_PROTOCOL);
	}
}
