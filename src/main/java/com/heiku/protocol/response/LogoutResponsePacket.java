package com.heiku.protocol.response;

import com.heiku.protocol.Packet;
import lombok.Data;

import static com.heiku.protocol.command.Command.LOGOUT_RESPONSE;

/**
 * @Author: Heiku
 * @Date: 2019/7/6
 */

@Data
public class LogoutResponsePacket extends Packet {

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LOGOUT_RESPONSE;
    }
}
