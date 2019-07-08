package com.heiku.client.handler;

import com.heiku.protocol.request.HeartBeatRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

public class HeartBeatTimerHandler extends ChannelInboundHandlerAdapter {

    private static final int HEARTBEAT_INTERVAL = 5;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        scheduleSendHeartBeat(ctx);

        super.channelActive(ctx);
    }

    /**
     * ctx的定时执行器，每隔5秒调用一次，发送心跳包
     *
     * @param ctx
     */
    private void scheduleSendHeartBeat(ChannelHandlerContext ctx){
        ctx.executor().schedule(() -> {

            if(ctx.channel().isActive()){

                ctx.writeAndFlush(new HeartBeatRequestPacket());
                scheduleSendHeartBeat(ctx);
            }
        }, HEARTBEAT_INTERVAL, TimeUnit.SECONDS);
    }
}
