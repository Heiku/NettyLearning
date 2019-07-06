package com.heiku.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 命令行接口
 *
 * @Author: Heiku
 * @Date: 2019/7/6
 */
public interface ConsoleCommand {

    void exec(Scanner scanner, Channel channel);
}
