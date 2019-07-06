package com.heiku.client.console;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 输入控制器
 *
 * @Author: Heiku
 * @Date: 2019/7/6
 */
public class ConsoleCommandManager implements ConsoleCommand {

    private Map<String, ConsoleCommand> consoleCommandMap;

    public ConsoleCommandManager(){
        consoleCommandMap = new HashMap<>();

        consoleCommandMap.put("sendToUser", new SendToUserConsoleCommand());
        consoleCommandMap.put("logout", new LogoutConsoleCommand());
        consoleCommandMap.put("createGroup", new CreateGroupConsoleCommand());
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        // 获取指令
        String command = scanner.next();

        // 获取指令对应的执行
        ConsoleCommand consoleCommand = consoleCommandMap.get(command);
        if (consoleCommand != null){
            consoleCommand.exec(scanner, channel);
        }else {
            System.err.println("无法识别["  + command + "]指令，请重新输入!");
        }
    }
}
