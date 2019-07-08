package com.heiku.client;

import com.heiku.client.console.ConsoleCommandManager;
import com.heiku.client.console.LoginConsoleCommand;
import com.heiku.client.handler.*;
import com.heiku.codec.PacketDecoder;
import com.heiku.codec.PacketEncoder;
import com.heiku.codec.Spliter;
import com.heiku.idle.IMIdleStateHandler;
import com.heiku.util.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


/**
 * @Author: Heiku
 * @Date: 2019/6/30
 */
public class NettyClient {

    private static final int MAX_RETRY = 5;
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8000;

    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        // 空闲检测
                        ch.pipeline().addLast(new IMIdleStateHandler());

                        // 添加自定义拆包器
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(new PacketDecoder());

                        // 登录响应处理器
                        ch.pipeline().addLast(new LoginResponseHandler());
                        // 登出响应处理器
                        ch.pipeline().addLast(new LogoutResponseHandler());
                        // 消息返回处理器
                        ch.pipeline().addLast(new MessageResponseHandler());
                        // 创建群聊响应处理器
                        ch.pipeline().addLast(new CreateGroupResponseHandler());
                        // 加入群聊响应处理器
                        ch.pipeline().addLast(new JoinGroupResponseHandler());
                        // 获取成员列表处理器
                        ch.pipeline().addLast(new ListGroupMembersResponseHandler());
                        //退出群聊响应处理器
                        ch.pipeline().addLast(new QuitGroupResponseHandler());

                        // 群聊响应处理器
                        ch.pipeline().addLast(new GroupMessageResponseHandler());

                        ch.pipeline().addLast(new PacketEncoder());

                        // 心跳定时器
                        ch.pipeline().addLast(new HeartBeatTimerHandler());
                    }
                });

        connect(bootstrap, HOST, PORT, MAX_RETRY);
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": 连接成功!");

                Channel channel = ((ChannelFuture) future).channel();
                startConsoleThread(channel);
            } else if (retry == 0) {
                System.err.println("重试次数已用完，放弃连接！");
            } else {
                // 第几次重连
                int order = (MAX_RETRY - retry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit
                        .SECONDS);
            }
        });
    }

    /**
     * 开启一个控制台线程
     *
     * @param channel
     */
    private static void startConsoleThread(Channel channel){
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
        Scanner scanner = new Scanner(System.in);

        new Thread(() -> {
            while (!Thread.interrupted()) {

                // 判断channel中是否属于登录状态
                if (!SessionUtil.hasLogin(channel)) {

                    // 如果为检测到channel中的session，那么调用登录控制台
                    loginConsoleCommand.exec(scanner, channel);
                } else {

                    // 否则，根据指令调用相应的控制台
                    consoleCommandManager.exec(scanner, channel);
                }
            }
        }).start();
    }

    private static void waitForLoginResponse(){
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /*private static void startConsoleThread(Channel channel){
        Scanner sc = new Scanner(System.in);
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        new Thread(() -> {
           while (!Thread.interrupted()){

               // 判断channel中是否属于登录状态
               if (!SessionUtil.hasLogin(channel)) {
                   System.out.println("请输入用户名登录：");

                   String userName = sc.nextLine();
                   loginRequestPacket.setUsername(userName);
                   loginRequestPacket.setPassword("sise");

                   // 发送登录请求数据包
                   channel.writeAndFlush(loginRequestPacket);

                   // 等待登录处理
                   waitForLoginResponse();
               }else {
                   String toUserId = sc.next();
                   String message = sc.next();

                   channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
               }
           }
        }).start();
    }*/

}
