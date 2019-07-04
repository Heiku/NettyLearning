package com.heiku.server.handler;

import com.heiku.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class AuthHandler extends ChannelInboundHandlerAdapter {

    /**
     * 如果每次发送消息都经历这样的 用户认证，会导致资源和性能的浪费
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!LoginUtil.hasLogin(ctx.channel())){
            ctx.channel().close();
        }else {

            // 已经验证登录状态了，将该逻辑的删除
            ctx.pipeline().remove(this);

            super.channelRead(ctx, msg);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if (LoginUtil.hasLogin(ctx.channel())){
            System.out.println("当前连接登录验证完毕，无需再次验证，AuthHandler 被移除");
        }else {
            super.handlerRemoved(ctx);
        }
    }
}
