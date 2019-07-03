package com.heiku.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author: Heiku
 * @Date: 2019/7/3
 *
 * ChannelHandler 生命周期
 *
 */
public class LifeCycleTestHandler extends ChannelInboundHandlerAdapter {

    /**
     * handlerAdded()：指的是检测到新连接之后，即 ch.pipeline().addLast(new LifeCycleTestHandler())之后的回调，
     *                  表示当前的channel中，已经成功添加了一个 handler 处理器
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("逻辑处理器被添加：handlerAdded()");
        super.handlerAdded(ctx);
    }

    /**
     * channelRegistered()：表示当前的channel的所有逻辑处理已经和某个NIO线程建立了绑定关系
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel 绑定到线程(NioEventLoop)：channelRegistered()");
        super.channelRegistered(ctx);
    }

    /**
     * channelActive()：当前channel的所有业务逻辑准备完毕（已经添加处理器完毕，并确定一个NIO线程进行绑定）
     *
     * 作用：1.配合channelInactive()可以统计当前的连接数
     *      2.可以实现对客户端ip黑白名单的过滤
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel 准备就绪：channelActive()");
        super.channelActive(ctx);
    }

    /**
     * channelRead()：客户端向服务端发来数据，每次都会回调此方法，表示有数据可读
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("channel 有数据可读：channelRead()");
        super.channelRead(ctx, msg);
    }

    /**
     * channelReadComplete()：服务端每次读完一次完整的数据之后，回调该方法，表示数据读取完毕
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel 某次数据读完：channelReadComplete()");
        super.channelReadComplete(ctx);
    }

    /**
     * channelInactive()：这条连接已经关闭，在TCP层面上已经不再是 ESTABLISH 状态
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel 被关闭：channelInActive()");
        super.channelInactive(ctx);
    }

    /**
     * channelUnregistered()：既然连接已经关闭，这条连接上绑定的线程就不需要对这条连接负责了，回调表示对应的线程移除这条连接的处理
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel 取消线程(NioEventLoop)的绑定：channelUnregistered");
        super.channelUnregistered(ctx);
    }



    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("逻辑处理器被移除：handlerRemoved()");
        super.handlerRemoved(ctx);
    }
}
