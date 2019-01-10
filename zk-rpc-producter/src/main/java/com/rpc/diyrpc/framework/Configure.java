package com.rpc.diyrpc.framework;

public class Configure {

	private String protocol;
	private String protocolString;
	private String hostname;
	private String portString;
	private Integer port;
	private String zookeeperHostname;
	private Integer zookeeperPort;
	
	public String getProtocol(String protocol) {
		if("http".equals(protocol)) {
			return ProviderProtocol.HTTP;
		}else if("jetty".equals(protocol)) {
			return ProviderProtocol.JETTY;
		}
		return ProviderProtocol.NETTY;
	}
	public String getProtocol() {
		if("http".equals(this.protocol)) {
			return ProviderProtocol.HTTP;
		}else if("jetty".equals(this.protocol)) {
			return ProviderProtocol.JETTY;
		}
		return ProviderProtocol.NETTY;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}

	public String getProtocolString() {
		return protocolString;
	}
	public void setProtocolString(String protocolString) {
		this.protocolString = protocolString;
	}
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
	
	public Configure(String protocolString, String hostname, String portString, String zookeeperHostname,
			Integer zookeeperPort) {
		super();
		this.protocolString = protocolString;
		this.hostname = hostname;
		this.portString = portString;
		this.zookeeperHostname = zookeeperHostname;
		this.zookeeperPort = zookeeperPort;
	}
	public String getPortString() {
		return portString;
	}
	public void setPortString(String portString) {
		this.portString = portString;
	}
	public Configure() {
		super();
	}
	
	@Override
	public String toString() {
		return "Configure [protocol=" + protocol + ", hostname=" + hostname + ", port=" + port + ", zookeeperHostname="
				+ zookeeperHostname + ", zookeeperPort=" + zookeeperPort + "]";
	}
	
}
