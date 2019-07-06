package com.heiku.server;

import com.heiku.client.handler.LogoutResponseHandler;
import com.heiku.codec.PacketDecoder;
import com.heiku.codec.PacketEncoder;
import com.heiku.codec.Spliter;
import com.heiku.server.handler.AuthHandler;
import com.heiku.server.handler.CreateGroupRequestHandler;
import com.heiku.server.handler.LoginRequestHandler;
import com.heiku.server.handler.MessageRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;

/**
 * @Author: Heiku
 * @Date: 2019/6/30
 */
public class NettyServer {

    private static final int PORT = 8000;

    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(bossGroup, workerGroup)
                // 表示系统用于临时存放已完成三次握手的请求的队列的最大长度，
                // 如果连接建立频繁，服务器处理创建新连接较慢，可以适当调大这个参数
                .option(ChannelOption.SO_BACKLOG, 1024)

                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel ch) {
                        // 添加自定义拆包器
                        ch.pipeline().addLast(new Spliter());

                        // 添加生命周期处理器
                        //ch.pipeline().addLast(new LifeCycleTestHandler());

                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginRequestHandler());
                        ch.pipeline().addLast(new LogoutResponseHandler());

                        // 添加热插拔认证处理器
                        //ch.pipeline().addLast(new AuthHandler());

                        // 客户端一对一消息处理器
                        ch.pipeline().addLast(new MessageRequestHandler());

                        // 创建群聊处理器
                        ch.pipeline().addLast(new CreateGroupRequestHandler());

                        ch.pipeline().addLast(new PacketEncoder());
                    }
                });

        bind(serverBootstrap, PORT);
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": 端口[" + port + "]绑定成功!");
            } else {
                System.err.println("端口[" + port + "]绑定失败!");
            }
        });
    }
}
