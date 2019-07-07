package com.heiku.server.handler;

import com.heiku.protocol.request.GroupMessageRequestPacket;
import com.heiku.protocol.response.GroupMessageResponsePacket;
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
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {

    public static final GroupMessageRequestHandler INSTANCE = new GroupMessageRequestHandler();

    private GroupMessageRequestHandler(){

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket requestPacket) throws Exception {

        // 1.拿到groupId,和发送者信息
        String groupId = requestPacket.getToGroupId();
        String fromUserName = SessionUtil.getSession(ctx.channel()).getUsername();

        // 2.取到channelGroup
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

        // 3.构造response
        GroupMessageResponsePacket responsePacket = new GroupMessageResponsePacket();
        responsePacket.setFromGroupId(groupId);
        responsePacket.setFromUser(fromUserName);
        responsePacket.setMessage(requestPacket.getMessage());

        channelGroup.writeAndFlush(responsePacket);
    }
}
