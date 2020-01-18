package com.example.demo.netty;
import java.util.HashMap;
import java.util.Map;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class DiscardServerHandler extends SimpleChannelHandler {
	Map<Object,Object> m =null;
	
	public DiscardServerHandler(Map<Object, Object> m) {
		super();
		this.m = m;
	}

	/**
	 * 接收消息
	 */
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {

		
		String s = (String) e.getMessage();
		System.out.println(s);
		
		//回写数据
	 
		
		 m.put(ctx, e);
		 m.forEach((x,y)->{
			 MessageEvent e1=(MessageEvent) y;
			 if(e.getRemoteAddress().equals(e1.getRemoteAddress())) {
				 System.out.println("no");
			 }else {
				 ChannelHandlerContext ctx1=(ChannelHandlerContext) x;
				 ctx1.getChannel().write(e.getMessage());
				 try {
					super.messageReceived(ctx1, e);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			 }
		 });
	 
	}

	/**
	 * 捕获异常
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
	 
		super.exceptionCaught(ctx, e);
	}

	/**
	 * 新连接
	 */
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
	 
		super.channelConnected(ctx, e);
	}

	/**
	 * 必须是链接已经建立，关闭通道的时候才会触发
	 */
	@Override
	public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		 
		super.channelDisconnected(ctx, e);
	}

	/**
	 * channel关闭的时候触发
	 */
	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		 
		super.channelClosed(ctx, e);
	}
}