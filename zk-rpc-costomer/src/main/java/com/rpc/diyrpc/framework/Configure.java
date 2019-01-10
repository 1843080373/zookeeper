package com.rpc.diyrpc.framework;

public class Configure {

	private String zookeeperHostname;
	private Integer zookeeperPort;
	
	public String getZookeeperHostname() {
		return zookeeperHostname;
	}
	public void setZookeeperHostname(String zookeeperHostname) {
		this.zookeeperHostname = zookeeperHostname;
	}
	public Integer getZookeeperPort() {
		return zookeeperPort;
	}
	public void setZookeeperPort(Integer zookeeperPort) {
		this.zookeeperPort = zookeeperPort;
	}
	public Configure(String zookeeperHostname, Integer zookeeperPort) {
		super();
		this.zookeeperHostname = zookeeperHostname;
		this.zookeeperPort = zookeeperPort;
	}
	public Configure() {
		super();
	}
	@Override
	public String toString() {
		return "Configure [zookeeperHostname=" + zookeeperHostname + ", zookeeperPort=" + zookeeperPort + "]";
	}
}
