package com.heiku.client.console;

import com.heiku.protocol.request.MessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author: Heiku
 * @Date: 2019/7/6
 */
public class SendToUserConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("发送消息给某个某个用户：");

        MessageRequestPacket messageRequestPacket = new MessageRequestPacket();

        // 读取输入
        String toUserId = scanner.next();
        String message = scanner.next();

        messageRequestPacket.setToUserId(toUserId);
        messageRequestPacket.setMessage(message);

        // 发送消息数据包
        channel.writeAndFlush(messageRequestPacket);
    }
}
