package com.heiku.client.handler;

import com.heiku.protocol.response.LogoutResponsePacket;
import com.heiku.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: Heiku
 * @Date: 2019/7/6
 */
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket logoutResponsePacket) throws Exception {

        // 登出，取消channel的session绑定
        SessionUtil.unBindSession(ctx.channel());
    }
}
