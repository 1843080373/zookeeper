package com.rpc.diyrpc.register;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.I0Itec.zkclient.ZkClient;

import com.alibaba.fastjson.JSONObject;
import com.rpc.diyrpc.framework.Configure;
import com.rpc.diyrpc.framework.RPCConfigure;
import com.rpc.diyrpc.framework.URL;

public class ZookeeperRegister {
	private static final String ROOT_NODE = "/zk_root";
	private static final String SELECT_PROTOCOL = "/select_protocol";
	 static final int SESSION_TIMEOUT = 5000000;
	//{服务名：{URL:实现类}}
	private static Map<String,Map<URL, Map<String,String>>> REGISTER = new HashMap<>();
	private static ZkClient zk = null;
	private static Configure conf = null;
	static {
		conf = RPCConfigure.getConfigure();
		String connection = conf.getZookeeperHostname() + ":" + conf.getZookeeperPort();
		zk = new ZkClient(connection,SESSION_TIMEOUT);
		createNode(SELECT_PROTOCOL);
	}
	private static void createNode(String nodePath) {
		if(zk.exists(nodePath)){
			zk.deleteRecursive(nodePath);
		}
		zk.createPersistent(nodePath,true);
	}
	
	private static Map<String,Map<URL, Map<String,String>>> readFromZK() {
		return zk.readData(ROOT_NODE);
	}

	public static Map<String,Object> random(String interfaceName) {
		REGISTER = readFromZK();
		System.out.println("--------"+JSONObject.toJSON(REGISTER));
		Map<URL,Map<String,String>> dataList=REGISTER.get(interfaceName);
		Set<URL> keys = dataList.keySet();
		int radom = new Random().nextInt(keys.size());
		URL thisNode = (URL) keys.toArray()[radom];
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("url", thisNode);
		map.put("protocol", dataList.get(thisNode).get("protocol"));
		map.put("implName", dataList.get(thisNode).get("implName"));
		System.out.println("thisNode===" + JSONObject.toJSONString(map));
		zk.writeData(SELECT_PROTOCOL, map);
		return map;
	}

}
