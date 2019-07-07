package com.heiku.util;

import com.heiku.attribute.Attributes;
import com.heiku.session.Session;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Heiku
 * @Date: 2019/7/6
 */
public class SessionUtil {

    private static final Map<String, Channel> userIdChannelMap = new ConcurrentHashMap<>();

    private static final Map<String, ChannelGroup> groupIdChannelGroupMap = new ConcurrentHashMap<>();

    public static void bindSession(Session session, Channel channel){
        userIdChannelMap.put(session.getUserId(), channel);

        // 配置channel的参数
        channel.attr(Attributes.SESSION).set(session);
    }


    public static void unBindSession(Channel channel){
        if (hasLogin(channel)) {
            Session session = getSession(channel);
            userIdChannelMap.remove(session.getUserId());

            channel.attr(Attributes.SESSION).set(null);

            System.out.println(session + " 退出登录!");
        }
    }

    /**
     * 判断channel 中是否存在session记录
     *
     * @param channel
     * @return
     */
    public static boolean hasLogin(Channel channel){
        return channel.hasAttr(Attributes.SESSION);
    }

    /**
     * 获取channel 中的session记录
     *
     * @param channel
     * @return
     */
    public static Session getSession(Channel channel){
        return channel.attr(Attributes.SESSION).get();
    }

    /**
     * 获取接受人的channel
     *
     * @param userId
     * @return
     */
    public static Channel getChannel(String userId){
        return userIdChannelMap.get(userId);
    }


    /**
     * 绑定channelGroup (groupId, channelGroup)
     *
     * @param groupId
     * @param channelGroup
     */
    public static void bindChannelGroup(String groupId, ChannelGroup channelGroup){
        groupIdChannelGroupMap.put(groupId, channelGroup);
    }


    /**
     *  获取对应的channelGroup
     *
     * @param groupId
     * @return
     */
    public static ChannelGroup getChannelGroup(String groupId){
        return groupIdChannelGroupMap.get(groupId);
    }

}
