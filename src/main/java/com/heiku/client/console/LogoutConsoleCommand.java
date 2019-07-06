package com.heiku.client.console;

import com.heiku.protocol.request.LogoutRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 登出命令
 *
 * @Author: Heiku
 * @Date: 2019/7/6
 */
public class LogoutConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        LogoutRequestPacket logoutRequestPacket = new LogoutRequestPacket();

        channel.writeAndFlush(logoutRequestPacket);
    }
}
