package com.heiku.protocol.response;

import com.heiku.protocol.Packet;
import lombok.Data;

import static com.heiku.protocol.command.Command.MESSAGE_RESPONSE;

@Data
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
