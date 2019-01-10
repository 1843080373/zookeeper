package com.rpc.diyrpc.protocol.netty;

import java.lang.reflect.Method;
import java.util.Map;

import com.rpc.diyrpc.framework.Invocation;
import com.rpc.diyrpc.framework.URL;
import com.rpc.diyrpc.register.ZookeeperRegister;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

@SuppressWarnings("deprecation")
public class NettyServerHandler extends ChannelInboundHandlerAdapter   {
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("选择了netty协议");
		System.out.println("服务端。。。。。。。。。收到" + msg);
		Invocation invocation = (Invocation) msg;
		Map<String,Object> url=ZookeeperRegister.random(invocation.getInterfaceName());
		Class<?> inplClass=Class.forName((String) url.get("implName"));
		Method method = inplClass.getDeclaredMethod(invocation.getMethodName(), invocation.getParamTypes());
		Object result = method.invoke(inplClass.newInstance(), invocation.getParams());
		System.out.println("服务端。。。。。。。。。发送" + result);
		ctx.channel().writeAndFlush(result);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("【exception is general】");
		ctx.close();
	}
}
