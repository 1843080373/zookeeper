package com.rpc.diyrpc.register;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.I0Itec.zkclient.ZkClient;

import com.alibaba.fastjson.JSONObject;
import com.rpc.diyrpc.framework.Configure;
import com.rpc.diyrpc.framework.RPCConfigure;
import com.rpc.diyrpc.framework.URL;
@SuppressWarnings("rawtypes")
public class ZookeeperRegister {

	private static final String ROOT_NODE="/zk_root";
	private static final String SELECT_PROTOCOL="/select_protocol";
	//{服务名：{URL:实现类}}
	private static Map<String,Map<URL,String>> REGISTER=new HashMap<>();
	//多个服务器节点{协议名:{服务名：{URL:实现类}}}
	private static Map<String,Map<String,Map<URL,String>>> REGISTER_LIST=new HashMap<>();
	private static ZkClient zk = null;
	private static Configure conf=null;
	static {
		conf=RPCConfigure.getConfigure();
		String connection=conf.getZookeeperHostname()+":"+conf.getZookeeperPort();
		zk = new ZkClient(connection);
		createNode(ROOT_NODE);
		createNode(SELECT_PROTOCOL);
	}
	public static void register(String interfaceName,Map<String,URL> urls,String implClass) {
		Set<String> keys=urls.keySet();
		for (String key : keys) {
			URL url=urls.get(key);
			register(key,interfaceName, url, implClass);
			REGISTER_LIST.put(key, REGISTER);
		}
		
	}
	
	private static void createNode(String nodePath) {
		if(zk.exists(nodePath)){
			zk.deleteRecursive(nodePath);
		}
		zk.createPersistent(nodePath,true);
	}
	
	public static void register(String protocol,String interfaceName,URL url,String implClass) {
		Map<URL,String> map=new HashMap<>();
		map.put(url, implClass);
		REGISTER.put(interfaceName, map);
		writeToZK(REGISTER_LIST);
	}
	
	private static void writeToZK(Map<String,Map<String,Map<URL,String>>> urls) {
		Set<String> keys=urls.keySet();
		for (String key : keys) {
			Map<String,Map<URL,String>> url=urls.get(key);
			createNodeAndWiteData(ROOT_NODE+"/"+key, url);
		}
		
	}

	public static void root() {
		System.out.println(ROOT_NODE+"-->"+JSONObject.toJSONString(REGISTER_LIST));
		zk.writeData(ROOT_NODE, REGISTER_LIST);
	}
	private static void createNodeAndWiteData(String nodePath,Object data) {
		createNode(nodePath);
		zk.writeData(nodePath, data);
		System.out.println(nodePath+"-->"+JSONObject.toJSONString(data));
	}

	public static String get(String interfaceName,URL url) {
		return REGISTER.get(interfaceName).get(url);
	}
	

	public static URL random(String interfaceName) {
		Map<String,URL> URL=zk.readData(SELECT_PROTOCOL);
		System.out.println(JSONObject.toJSON(URL));
		return (com.rpc.diyrpc.framework.URL) URL.entrySet().toArray()[0];
	}
}
