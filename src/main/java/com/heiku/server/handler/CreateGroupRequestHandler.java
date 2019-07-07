package com.heiku.server.handler;

import com.heiku.protocol.request.CreateGroupRequestPacket;
import com.heiku.protocol.response.CreateGroupResponsePacket;
import com.heiku.util.IDUtil;
import com.heiku.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Heiku
 * @Date: 2019/7/6
 */
@ChannelHandler.Sharable
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    public static final CreateGroupRequestHandler INSTANCE = new CreateGroupRequestHandler();

    private CreateGroupRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket createGroupRequestPacket) throws Exception {
        // 获取群聊的userIds
        List<String> userIdList = createGroupRequestPacket.getUserIdList();

        // 返回的用户名列表
        List<String> userNameList = new ArrayList<>();


        // 1.创建channelGroup分组
        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());

        // 2.筛选出加入群聊的用户名和channel
        for (String userId : userIdList){
            Channel channel = SessionUtil.getChannel(userId);
            if (channel != null){
                channelGroup.add(channel);
                userNameList.add(SessionUtil.getSession(channel).getUsername());
            }
        }

        // 3.创建群聊的结果响应
        String groupId = IDUtil.randomId();
        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        createGroupResponsePacket.setSuccess(true);
        createGroupResponsePacket.setGroupId(groupId);
        createGroupResponsePacket.setUserNameList(userNameList);

        // 4.给每个客户端发送群通知
        channelGroup.writeAndFlush(createGroupResponsePacket);

        // 服务端显示
        System.out.print("群创建成功，id 为[" + createGroupResponsePacket.getGroupId() + "], ");
        System.out.println("群里面有：" + createGroupResponsePacket.getUserNameList());

        // 5.保存群聊的信息
        SessionUtil.bindChannelGroup(groupId, channelGroup);
    }
}
