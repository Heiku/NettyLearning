package com.heiku.server.handler;

import com.heiku.protocol.request.LoginRequestPacket;
import com.heiku.protocol.response.LoginResponsePacket;
import com.heiku.session.Session;
import com.heiku.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.UUID;


/**
 * 具体的登录请求handler
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        System.out.println(new Date() + ": 收到客户端登录请求……");

        // 初始化返回登录
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        loginResponsePacket.setUserName(loginRequestPacket.getUsername());

        if (valid(loginRequestPacket)) {
            loginResponsePacket.setSuccess(true);

            // 暂时随机生成用户id
            String userId = randomUserId();
            loginResponsePacket.setUserId(userId);

            System.out.println("[" + loginRequestPacket.getUsername() + "]登录成功");

            // 在channel中记录用户已经登录
            // 绑定Session Map(session, channel)
            SessionUtil.bindSession(new Session(userId, loginRequestPacket.getUsername()), ctx.channel());

        } else {
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
            System.out.println(new Date() + ": 登录失败!");
        }

        // 登录响应
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    /**
     * 断开连接取消session绑定
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }


    private static String randomUserId(){
        return UUID.randomUUID().toString().split("-")[0];
    }
}
