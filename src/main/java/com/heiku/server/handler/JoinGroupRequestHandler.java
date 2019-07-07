package com.heiku.server.handler;

import com.heiku.protocol.request.JoinGroupRequestPacket;
import com.heiku.protocol.response.JoinGroupResponsePacket;
import com.heiku.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @Author: Heiku
 * @Date: 2019/7/7
 */

@ChannelHandler.Sharable
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {

    public static final JoinGroupRequestHandler INSTANCE = new JoinGroupRequestHandler();

    private JoinGroupRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket joinGroupRequestPacket) throws Exception {

        // 1.获取对应的groupId
        String groupId = joinGroupRequestPacket.getGroupId();

        // 2.获取对应的channelGroup
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.add(ctx.channel());

        // 3.构造response
        JoinGroupResponsePacket joinGroupResponsePacket = new JoinGroupResponsePacket();
        joinGroupResponsePacket.setGroupId(groupId);
        joinGroupResponsePacket.setSuccess(true);

        // 4.记录保存
        SessionUtil.bindChannelGroup(groupId, channelGroup);

        ctx.channel().writeAndFlush(joinGroupResponsePacket);
    }
}
