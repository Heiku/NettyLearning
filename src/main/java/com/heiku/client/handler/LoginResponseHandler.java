package com.heiku.client.handler;

import com.heiku.protocol.response.LoginResponsePacket;
import com.heiku.session.Session;
import com.heiku.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) throws Exception {
        String userId = loginResponsePacket.getUserId();
        String userName = loginResponsePacket.getUserName();

        if (loginResponsePacket.isSuccess()){
            System.out.println("[" + userName + "]登录成功, userId为：" + userId);

            SessionUtil.bindSession(new Session(userId, userName), ctx.channel());
        }else {

            System.out.println("[" + userName + "]登录失败，原因是：" + loginResponsePacket.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端的连接已经关闭！");
    }
}
