package com.heiku.client.console;

import com.heiku.protocol.request.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: Heiku
 * @Date: 2019/7/6
 */
public class CreateGroupConsoleCommand implements ConsoleCommand {

    private static final String USER_ID_SPLITER = ",";

    @Override
    public void exec(Scanner scanner, Channel channel) {
        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();

        System.out.print("【拉人群聊】输入 userId 列表，userId 之间英文逗号隔开：");

        String userIds = scanner.next();
        createGroupRequestPacket.setUserIdList(Arrays.asList(userIds.split(USER_ID_SPLITER)));

        // 发送创建组数据包
        channel.writeAndFlush(createGroupRequestPacket);
    }
}
