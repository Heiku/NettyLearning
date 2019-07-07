package com.heiku.server.handler;

import com.heiku.protocol.request.QuitGroupRequestPacket;
import com.heiku.protocol.response.QuitGroupResponsePacket;
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
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {

    public static final QuitGroupRequestHandler INSTANCE = new QuitGroupRequestHandler();

    private QuitGroupRequestHandler() {

    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket quitGroupRequestPacket) throws Exception {

        // 1.获取groupId
        String groupId = quitGroupRequestPacket.getGroupId();

        // 2.得到channelGroup，移除对应的channel
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.remove(ctx.channel());

        // 3.构造response
        QuitGroupResponsePacket responsePacket = new QuitGroupResponsePacket();
        responsePacket.setGroupId(groupId);
        responsePacket.setSuccess(true);

        // 4.保存记录
        SessionUtil.bindChannelGroup(groupId, channelGroup);

        ctx.channel().writeAndFlush(responsePacket);
    }
}
