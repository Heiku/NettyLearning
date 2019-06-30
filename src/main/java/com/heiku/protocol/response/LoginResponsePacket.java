package com.heiku.protocol.response;

import com.heiku.protocol.Packet;
import lombok.Data;

import static com.heiku.protocol.command.Command.LOGIN_RESPONSE;
/**
 * @Author: Heiku
 * @Date: 2019/6/30
 *
 * 登录返回包
 */
@Data
public class LoginResponsePacket extends Packet {

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
