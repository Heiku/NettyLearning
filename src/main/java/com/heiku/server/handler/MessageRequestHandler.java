package com.heiku.server.handler;

import com.heiku.protocol.request.MessageRequestPacket;
import com.heiku.protocol.response.MessageResponsePacket;
import com.heiku.session.Session;
import com.heiku.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {

        // 1.获取当前的Session信息
        Session session = SessionUtil.getSession(ctx.channel());

        // 2.构造返回的packet
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromUserId(session.getUserId());
        messageResponsePacket.setFromUserName(session.getUsername());
        messageResponsePacket.setMessage(messageRequestPacket.getMessage());

        // 3.获取接收方的channel
        Channel channel = SessionUtil.getChannel(messageRequestPacket.getToUserId());

        // 4.发送消息
        if (channel != null && SessionUtil.hasLogin(channel)) {
            channel.writeAndFlush(messageResponsePacket);
        } else {
            System.err.println("[" + messageRequestPacket.getToUserId() + "]不在线，发送失败！");
        }
    }
}
