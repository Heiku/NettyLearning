package com.heiku.codec;

import com.heiku.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 自定义长度域拆包器
 *
 * netty粘包/拆包：
 *      虽然netty在应用层是以ByteBuf的方式发送数据，在底层操作系统仍然是以字节流发送数据。
 *      因此，数据到了服务端，也是按照字节流的方式读入，到了netty层面，重新拼接成 ByteBuf，这里的ByteBuf与客户端顺序发送的 ByteBuf 可能不对等。
 *
 *
 * 自定义协议：魔数（4）+ 版本号（1）+ 序列化算法（1）+ 指令（1） + 数据长度（4）+ 数据（N）
 * 长度域偏移量 = 4 + 1 + 1 + 1 = 7
 * 长度域长度 = 4
 *
 */
public class Spliter extends LengthFieldBasedFrameDecoder {

    private static final int LENGTH_FIELD_OFFSET = 7;
    private static final int LENGTH_FIELD_LENGTH = 4;


    // 采用LengthFieldBasedFrameDecoder 的默认构造方法
    public Spliter(){
        super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        // 屏蔽非本协议的客户端
        if (in.getInt(in.readerIndex()) != PacketCodeC.MAGIC_NUMBER){
            ctx.channel().close();
            return null;
        }

        return super.decode(ctx, in);
    }
}
