package com.heiku.protocol.request;

import com.heiku.protocol.Packet;
import lombok.Data;

import static com.heiku.protocol.command.Command.LOGOUT_REQUEST;

/**
 * @Author: Heiku
 * @Date: 2019/7/6
 */

@Data
public class LogoutRequestPacket extends Packet {

    @Override
    public Byte getCommand() {
        return LOGOUT_REQUEST;
    }
}
