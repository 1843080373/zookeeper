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
	// 多个服务器节点{协议名:{服务名：{URL:实现类}}}
	private static Map<String, Map<String, Map<URL, String>>> REGISTER_LIST = new HashMap<>();
	private static ZkClient zk = null;
	private static Configure conf = null;
	static {
		conf = RPCConfigure.getConfigure();
		String connection = conf.getZookeeperHostname() + ":" + conf.getZookeeperPort();
		zk = new ZkClient(connection);
	}

	private static Map<String, Map<String, Map<URL, String>>> readFromZK() {
		return zk.readData(ROOT_NODE);
	}

	public static Map<String,URL> random(String interfaceName) {
		System.out.println("random");
		REGISTER_LIST = readFromZK();
		System.out.println(JSONObject.toJSON(REGISTER_LIST));
		Set<String> keys = REGISTER_LIST.keySet();
		int radom = new Random().nextInt(keys.size());
		Object thisNode = keys.toArray()[radom];
		System.out.println("thisNode===" + thisNode);
		REGISTER_LIST.get(thisNode.toString());
		Map<URL, String> REGISTER = REGISTER_LIST.get(thisNode.toString()).get(interfaceName);
		URL URL = (URL) REGISTER.keySet().toArray()[0];
		Map<String, URL> SELECT_PROTOCOL_DATA = new HashMap<>();
		SELECT_PROTOCOL_DATA.put(thisNode.toString(), URL);
		zk.writeData(SELECT_PROTOCOL, SELECT_PROTOCOL_DATA);
		System.out.println("thisNode===" + JSONObject.toJSONString(SELECT_PROTOCOL));
		zk.close();
		return SELECT_PROTOCOL_DATA;
	}

}
