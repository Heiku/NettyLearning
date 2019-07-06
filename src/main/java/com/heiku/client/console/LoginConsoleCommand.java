package com.heiku.client.console;

import com.heiku.protocol.request.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 登录指令执行
 *
 * @Author: Heiku
 * @Date: 2019/7/6
 */
public class LoginConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        // 读取输入
        System.out.print("输入用户名登录: ");
        loginRequestPacket.setUsername(scanner.nextLine());
        loginRequestPacket.setPassword("sise");

        // 发送登录数据包
        channel.writeAndFlush(loginRequestPacket);

        // 模拟等待响应
        waitForLoginResponse();
    }


    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}
